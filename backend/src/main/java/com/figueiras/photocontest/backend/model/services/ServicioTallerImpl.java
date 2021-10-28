package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.daos.*;
import com.figueiras.photocontest.backend.model.entities.*;
import com.figueiras.photocontest.backend.model.exceptions.CampoVacioException;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.model.exceptions.ParseFormatException;
import com.figueiras.photocontest.backend.rest.conversor.TallerConversor;
import com.figueiras.photocontest.backend.rest.conversor.UsuarioConversor;
import com.figueiras.photocontest.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class ServicioTallerImpl implements ServicioTaller{

    @Autowired
    private PuestoTallerDao puestoTallerDao;

    @Autowired
    private AsistenciasDao asistenciaDao;

    @Autowired
    private HorariosDao horariosDao;

    @Autowired
    private PlanHorariosDao planHorariosDao;

    @Autowired
    private VehiculoDao vehiculoDao;

    @Autowired
    private EstadoAsistenciasDao estadoAsistenciasDAO;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private TipoAsistenciasDao tipoAsistenciasDao;

    @Autowired
    private TrabajoDao trabajoDao;

    @Override
    public Asistencia asignarAsistenciaPuesto(AsistenciaPuestoTDto asistenciaPuestoTDto) throws InstanceNotFoundException {
        Optional<Asistencia> asisOptional = asistenciaDao.findById(asistenciaPuestoTDto.getIdAsistencia());
        if(asisOptional.isEmpty()){
            throw new InstanceNotFoundException("entidades.vehiculo.idVehiculo", asistenciaPuestoTDto.getIdAsistencia());
        }

        Optional<PuestoTaller> puestoOptional = puestoTallerDao.findById(asistenciaPuestoTDto.getIdPuesto());
        if(puestoOptional.isEmpty()){
            throw new InstanceNotFoundException("entidades.puestoTaller.puesto", asistenciaPuestoTDto.getIdPuesto());
        }

        Asistencia asistencia = asisOptional.get();
        asistencia.setPuesto(puestoOptional.get());
        asistenciaDao.save(asistencia);

        return asistencia;
    }

    @Override
    public AsistenciaCompletaFranjaHDto asignarAsistenciaFranjaHoraria(AsistenciaFranjaHorariaDto asistenciaFranjaHDto) throws InstanceNotFoundException {
        Optional<Asistencia> asisOptional = asistenciaDao.findById(asistenciaFranjaHDto.getIdAsistencia());
        if(asisOptional.isEmpty()){
            throw new InstanceNotFoundException("entidades.vehiculo.idVehiculo", asistenciaFranjaHDto.getIdAsistencia());
        }

        final Long[] idHorarioExcepcion = {-1L};
        asistenciaFranjaHDto.getFranjasHorarias().forEach((idHorario, franjaHoraria) -> {
            Optional<Horarios> horarioOpt = horariosDao.findById(idHorario);
            if (horarioOpt.isEmpty()) {
                idHorarioExcepcion[0] = idHorario;
            }
        });

        if (idHorarioExcepcion[0] == -1L)
            throw new InstanceNotFoundException("entidades.horarios.idHorario", idHorarioExcepcion[0]);

        asistenciaFranjaHDto.getFranjasHorarias().forEach((idHorario, franjaHoraria) -> {
            Optional<Horarios> horarioOpt = horariosDao.findById(idHorario);
            PlanHorarios planH = new PlanHorarios();
            planH.setAsistencia(asisOptional.get());
            planH.setFranjaHoraria(horarioOpt.get());
            planHorariosDao.save(planH);
        });

        AsistenciaCompletaFranjaHDto asistenciaC = new AsistenciaCompletaFranjaHDto();
        asistenciaC.setIdAsistencia(asisOptional.get().getIdAsistencia());
        asistenciaC.setEstado(TallerConversor.toEstAsistenciasDto(asisOptional.get().getEstado()));
        asistenciaC.setPuesto(TallerConversor.toPuestoTDto(asisOptional.get().getPuesto()));
        asistenciaC.setMecanico(UsuarioConversor.toUsuarioDto(asisOptional.get().getMecanico()));
        asistenciaC.setTipo(TallerConversor.toTiposAsistenciasDto(asisOptional.get().getTipo()));
        asistenciaC.setFecha(asisOptional.get().getFecha().toString());
        asistenciaC.setFranjasHorarias(TallerConversor.toFranjasHorariasDto(findHoraByAsistencia(asisOptional.get().getIdAsistencia())));

        return asistenciaC;
    }

    @Override
    public Block<PlanHorarios> findHoraByAsistencia(Long idAsistencia){
        return planHorariosDao.findByIdAsistencia(idAsistencia);
    }

    @Override
    public Asistencia createAsistencia(AsistenciasDto asistenciasDto) throws InstanceNotFoundException, ParseFormatException {
        Optional<EstadoAsistencias> estadoAsistencias = estadoAsistenciasDAO.findById(asistenciasDto.getEstado());
        if(estadoAsistencias.isEmpty()){
            throw new InstanceNotFoundException("entidades.estadoAsistencias.idEstado", asistenciasDto.getEstado());
        }

        Optional<Usuario> usuario = usuarioDao.findById(asistenciasDto.getMecanico());
        if(usuario.isEmpty()){
            throw new InstanceNotFoundException("entidades.usuario.idUsuario", asistenciasDto.getMecanico());
        }

        Optional<TipoAsistencias> tipoAsistencia = tipoAsistenciasDao.findById(asistenciasDto.getTipo());
        if(tipoAsistencia.isEmpty()){
            throw new InstanceNotFoundException("entidades.tiposAsistencias.idTipo", asistenciasDto.getTipo());
        }

        Optional<PuestoTaller> puestoTaller = puestoTallerDao.findById(asistenciasDto.getPuestoTaller());
        if(puestoTaller.isEmpty()){
            throw new InstanceNotFoundException("entidades.puestoTaller.idPuestoTaller", asistenciasDto.getPuestoTaller());
        }

        Optional<Trabajo> trabajo = trabajoDao.findById(asistenciasDto.getIdTrabajo());
        if(trabajo.isEmpty()){
            throw new InstanceNotFoundException("entidades.trabajo.idTrabajo", asistenciasDto.getIdTrabajo());
        }

        SimpleDateFormat sdf = new SimpleDateFormat();
        Date date;
        try{
            date = sdf.parse(asistenciasDto.getFecha());
        }catch (Exception e){
            throw new ParseFormatException(asistenciasDto.getFecha());
        }

        Asistencia asistencia = new Asistencia();
        asistencia.setEstado(estadoAsistencias.get());
        asistencia.setMecanico(usuario.get());
        asistencia.setPuesto(puestoTaller.get());
        asistencia.setFecha(date);
        asistencia.setTipo(tipoAsistencia.get());
        asistencia.setTrabajo(trabajo.get());
        asistenciaDao.save(asistencia);


        return asistencia;
    }

    @Override
    public Slice<Horarios> getHorariosDisponibles(int page, int size) {
        return horariosDao.getEtiquetasOrderById(PageRequest.of(page, size));
    }

    @Override
    public Trabajo createTrabajo(TrabajoDto trabajoDto) throws InstanceNotFoundException, CampoVacioException {
        Optional<Usuario> usuario = usuarioDao.findById(trabajoDto.getVehiculo());
        if(usuario.isEmpty())
            throw new InstanceNotFoundException("entidades.usuario.idUsuario", trabajoDto.getVehiculo());

        Optional<Vehiculo> veh = vehiculoDao.findById(trabajoDto.getVehiculo());
        if(veh.isEmpty()){
            throw new InstanceNotFoundException("entidades.vehiculo.idVehiculo", trabajoDto.getVehiculo());
        }

        if (trabajoDto.getNombre().isEmpty())
            throw new CampoVacioException("entidades.trabajo.nombre", trabajoDto.getNombre());

        Trabajo trabajo = new Trabajo();
        trabajo.setVehiculo(veh.get());
        trabajo.setNombre(trabajoDto.getNombre());
        trabajo.setDescripcion(trabajoDto.getDescripcion());
        trabajoDao.save(trabajo);

        return trabajo;
    }

    @Override
    public Block<Asistencia> findAllAsistencias(int page, int size) {
        Slice<Asistencia> asistencias = asistenciaDao.findAll(PageRequest.of(page, size));

        return new Block<>(asistencias.getContent(), asistencias.hasNext());
    }
}
