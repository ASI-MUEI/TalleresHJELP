package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.daos.*;
import com.figueiras.photocontest.backend.model.entities.*;
import com.figueiras.photocontest.backend.model.exceptions.CampoVacioException;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.model.exceptions.ParseFormatException;
import com.figueiras.photocontest.backend.model.exceptions.StateErrorException;
import com.figueiras.photocontest.backend.rest.conversor.TallerConversor;
import com.figueiras.photocontest.backend.rest.conversor.UsuarioConversor;
import com.figueiras.photocontest.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private VehiculoDao vehiculoDao;

    @Autowired
    private AsistenciaHorarioDao asistenciaHorarioDao;

    @Autowired
    private AsistenciaPiezaDao asistenciaPiezaDao;

    @Autowired
    private EstadoTrabajosDao estadoTrabajosDao;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private TipoAsistenciasDao tipoAsistenciasDao;

    @Autowired
    private PiezaDao piezaDao;

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

    public void actualizaFechaYHoraAsistencia(AsistenciaFranjaHorariaDto asistenciaFranjaHorariaDto) throws InstanceNotFoundException{
        Optional<Asistencia> asisOptional = asistenciaDao.findById(asistenciaFranjaHorariaDto.getIdAsistencia());
        if(asisOptional.isEmpty()){
            throw new InstanceNotFoundException("entidades.vehiculo.idVehiculo", asistenciaFranjaHorariaDto.getIdAsistencia());
        }

        Asistencia asistencia = asisOptional.get();
        String[] fecha_tabla = asistenciaFranjaHorariaDto.getFecha().split("-");
        LocalDateTime fecha = LocalDateTime.of(Integer.parseInt(fecha_tabla[0]), Integer.parseInt(fecha_tabla[1]),
                Integer.parseInt(fecha_tabla[2]), 0, 0, 0, 0);
        asistencia.setFecha(fecha);
        asistenciaDao.save(asistencia);

        asignarAsistenciaFranjaHoraria(asistenciaFranjaHorariaDto);
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

        AsistenciaCompletaFranjaHDto asistenciaC = new AsistenciaCompletaFranjaHDto();
        asistenciaC.setIdAsistencia(asisOptional.get().getIdAsistencia());
        asistenciaC.setPuesto(TallerConversor.toPuestoTDto(asisOptional.get().getPuesto()));
        List<UsuarioDto> mecanicos = new ArrayList<>();
        for (Usuario u : asisOptional.get().getMecanicos()) {
            UsuarioDto uDto = UsuarioConversor.toUsuarioDto(u);
            mecanicos.add(uDto);

        }
        asistenciaC.setMecanicos(mecanicos);
        asistenciaC.setTipo(TallerConversor.toTiposAsistenciasDto(asisOptional.get().getTipo()));
        asistenciaC.setFecha(asisOptional.get().getFecha().toString());

        return asistenciaC;
    }

    @Override
    public Asistencia createAsistencia(AsistenciasDto asistenciasDto) throws InstanceNotFoundException, ParseFormatException {
        List<Usuario> mecanicos = new ArrayList<>();

        for (MecanicoAsistenciaDto mDto : asistenciasDto.getMecanicos()) {
            Optional<Usuario> usuario = usuarioDao.findById(mDto.getIdMecanico());
            if(usuario.isEmpty()){
                throw new InstanceNotFoundException("entidades.usuario.idUsuario", mDto.getIdMecanico());
            }
            mecanicos.add(usuario.get());
        }

        Optional<TipoAsistencias> tipo = tipoAsistenciasDao.findById(asistenciasDto.getTipo());
        if(tipo.isEmpty()){
            throw new InstanceNotFoundException("entidades.asistencias.tipo", asistenciasDto.getTipo());
        }

        Optional<PuestoTaller> puestoTaller = puestoTallerDao.findById(asistenciasDto.getElevador());
        if(puestoTaller.isEmpty()){
            throw new InstanceNotFoundException("entidades.puestoTaller.idPuestoTaller", asistenciasDto.getElevador());
        }

        Optional<Trabajo> trabajo = trabajoDao.findById(asistenciasDto.getIdTrabajo());
        if(trabajo.isEmpty()){
            throw new InstanceNotFoundException("entidades.trabajo.idTrabajo", asistenciasDto.getIdTrabajo());
        }

        String[] fecha_tabla = asistenciasDto.getFecha().split("-");

        LocalDateTime fecha = LocalDateTime.of(Integer.parseInt(fecha_tabla[0]), Integer.parseInt(fecha_tabla[1]),
                Integer.parseInt(fecha_tabla[2]), 0, 0, 0, 0);

        Asistencia asistencia = new Asistencia();
        asistencia.setMecanicos(mecanicos);
        asistencia.setPuesto(puestoTaller.get());
        asistencia.setFecha(fecha);
        asistencia.setPrecio(asistenciasDto.getPrecio());
        asistencia.setDuracionEstimada(asistenciasDto.getDuracionEstimada());
        asistencia.setTrabajo(trabajo.get());
        asistencia.setTipo(tipo.get());
        asistencia.setDescripcion(asistenciasDto.getDescripcion());
        asistencia.setPeritaje(asistenciasDto.getPeritaje());
        asistencia = asistenciaDao.save(asistencia);

        for (HorariosAsistenciasDto horario : asistenciasDto.getHorasDeTrabajo()) {
            asistenciaHorarioDao.save(new AsistenciaHorario(asistencia.getIdAsistencia(),horario.getId()));
        }

        for (PiezasAsistenciasDto pieza : asistenciasDto.getPiezasReparacion()) {
            asistenciaPiezaDao.save(new AsistenciaPieza(asistencia.getIdAsistencia(),pieza.getIdPieza()));
        }

        return asistencia;
    }

    @Override
    public List<Horarios> getHorariosDisponibles() {
        return horariosDao.getEtiquetasOrderById();
    }

    @Override
    public ArrayList<List<Horarios>> getHorariosLibresporFecha(String fecha){
        List<Asistencia> asistencias = findAllAsistenciasPorFecha(fecha);
        List<Horarios> hLElevador1 = new ArrayList<>();
        List<Horarios> hLElevador2 = new ArrayList<>();
        List<Horarios> hLElevador3 = new ArrayList<>();
        List<Horarios> hLElevador4 = new ArrayList<>();
        List<Horarios> hLElevador5 = new ArrayList<>();
        for (Asistencia asistencia:asistencias){
            switch (String.valueOf(asistencia.getPuesto().getIdPuesto())){
                case "1":
                    hLElevador1.addAll(asistencia.getHorarios());
                case "2":
                    hLElevador2.addAll(asistencia.getHorarios());
                case "3":
                    hLElevador3.addAll(asistencia.getHorarios());
                case "4":
                    hLElevador4.addAll(asistencia.getHorarios());
                case "5":
                    hLElevador5.addAll(asistencia.getHorarios());
            }
        }

        ArrayList result = new ArrayList();
        result.add(hLElevador1);
        result.add(hLElevador2);
        result.add(hLElevador3);
        result.add(hLElevador4);
        result.add(hLElevador5);
        return result;

    }

    @Override
    public TipoAsistencias crearTipoAsistencia(String nombre, String descripcion) {
        return tipoAsistenciasDao.save(new TipoAsistencias(nombre, descripcion));
    }

    @Override
    public Slice<TipoAsistencias> getTipoAssitencias(){
        return tipoAsistenciasDao.findAll(PageRequest.of(1000,1000));
    }

    @Override
    public Slice<Pieza> getPiezasByAsistencia(Long idAsistencia, int page, int size) throws InstanceNotFoundException {
        Optional<Asistencia> asistenciaOpt = asistenciaDao.findById(idAsistencia);
        if (asistenciaOpt.isEmpty())
            throw new InstanceNotFoundException("entidades.asistencia.idAsistencia", idAsistencia);
        Slice<AsistenciaPieza> piezas = asistenciaPiezaDao.findPiezasByIdAsistencia(idAsistencia, PageRequest.of(page, size));

        List<Pieza> listadoPiezas = new ArrayList<>();
        for (AsistenciaPieza ap: piezas) {
            Optional<Pieza> p = piezaDao.findById(ap.getIdPieza());
            p.ifPresent(listadoPiezas::add);
        }

        Pageable p = PageRequest.of(1,5);

        return new SliceImpl<>(listadoPiezas, p, piezas.hasNext());
    }

    @Override
    public Asistencia asignarAsistenciaPieza(AsistenciaNuevaPiezaDto asistenciaNuevaPiezaDto) throws InstanceNotFoundException {
        Optional<Asistencia> asisOptional = asistenciaDao.findById(asistenciaNuevaPiezaDto.getIdAsistencia());
        if(asisOptional.isEmpty()){
            throw new InstanceNotFoundException("entidades.vehiculo.idVehiculo", asistenciaNuevaPiezaDto.getIdAsistencia());
        }

        Optional<Pieza> pieza = piezaDao.findById(asistenciaNuevaPiezaDto.getIdPieza());
        if(pieza.isEmpty()){
            throw new InstanceNotFoundException("entidades.pieza.idpieza", asistenciaNuevaPiezaDto.getIdPieza());
        }

        Asistencia asistencia = asisOptional.get();
        List<Pieza> piezas = asistencia.getPiezas();
        if(piezas.contains(pieza.get())){
            Optional<AsistenciaPieza> apOpt = asistenciaPiezaDao.findByIdPiezaIdAsistencia(pieza.get().getIdPieza(), asistencia.getIdAsistencia());
            if(apOpt.isPresent()){
                AsistenciaPieza ap = apOpt.get();
                ap.setNumeroUnidades(ap.getNumeroUnidades() + asistenciaNuevaPiezaDto.getNumeroPiezas());
                asistenciaPiezaDao.save(ap);
            }
        }else{
            AsistenciaPieza nuevaAp = new AsistenciaPieza();
            nuevaAp.setIdPieza(pieza.get().getIdPieza());
            nuevaAp.setIdAsistencia(asistencia.getIdAsistencia());
            nuevaAp.setNumeroUnidades(asistenciaNuevaPiezaDto.getNumeroPiezas());
            asistenciaPiezaDao.save(nuevaAp);
        }

        return asistencia;
    }

    @Override
    public Asistencia deleteAsistenciaPieza(AsistenciaNuevaPiezaDto asistenciaNuevaPiezaDto) throws InstanceNotFoundException {
        Optional<Asistencia> asisOptional = asistenciaDao.findById(asistenciaNuevaPiezaDto.getIdAsistencia());
        if(asisOptional.isEmpty()){
            throw new InstanceNotFoundException("entidades.vehiculo.idVehiculo", asistenciaNuevaPiezaDto.getIdAsistencia());
        }

        Optional<Pieza> pieza = piezaDao.findById(asistenciaNuevaPiezaDto.getIdPieza());
        if(pieza.isEmpty()){
            throw new InstanceNotFoundException("entidades.asistenciaPieza.idPieza", asistenciaNuevaPiezaDto.getIdPieza());
        }

        Optional<AsistenciaPieza> apOpt = asistenciaPiezaDao.findByIdPiezaIdAsistencia(asistenciaNuevaPiezaDto.getIdPieza(), asistenciaNuevaPiezaDto.getIdAsistencia());
        apOpt.ifPresent(asistenciaPieza -> asistenciaPiezaDao.delete(asistenciaPieza));

        Asistencia asistencia = asisOptional.get();
        List<Pieza> piezas = asistencia.getPiezas();
        piezas.remove(pieza.get());
        asistencia.setPiezas(piezas);
        asistenciaDao.save(asistencia);

        return asistencia;
    }

    @Override
    public String getFactura(Long idTrabajo) throws InstanceNotFoundException, StateErrorException {
        Optional<Trabajo> trabajoOpt = trabajoDao.findById(idTrabajo);
        if (trabajoOpt.isEmpty())
            throw new InstanceNotFoundException("entidades.trabajo.idTrabajo", idTrabajo);

        if (trabajoOpt.get().getEstado().getIdEstado() != 2){
            throw new StateErrorException("entidades.trabajo.estado", trabajoOpt.get().getEstado().getNombre());
        }

        StringBuilder factura = new StringBuilder();
        List<Asistencia> asistencias = asistenciaDao.findByIdTrabajo(idTrabajo);
        for (Asistencia asistencia : asistencias){
            factura.append("Asistencia ").append(asistencia.getIdAsistencia()).append(":").append(asistencia.getDescripcion()).append("\n");
            factura.append("Duración: ").append(asistencia.getDuracionEstimada());
            factura.append("Precio").append(asistencia.getPrecio()).append("\n");
            if (!asistencia.getPiezas().isEmpty()){
                factura.append("Piezas utilizadas:\n");
                for (Pieza pieza : asistencia.getPiezas()){
                    factura.append("Pieza: ").append(pieza.getNombre()).append("\n");
                }
            }
        }
        return factura.toString();
    }

    @Override
    public Trabajo createTrabajo(TrabajoDto trabajoDto) throws InstanceNotFoundException, CampoVacioException {
        //Optional<Usuario> usuario = usuarioDao.findById(trabajoDto.getVehiculo());
        //if(usuario.isEmpty())
        //    throw new InstanceNotFoundException("entidades.usuario.idUsuario", trabajoDto.getVehiculo());

        Optional<Vehiculo> veh = vehiculoDao.findByMatricula(trabajoDto.getMatricula());
        if(veh.isEmpty()){
            throw new InstanceNotFoundException("entidades.vehiculo.idVehiculo", trabajoDto.getMatricula());
        }

        Optional<EstadoTrabajo> estadoOpt = estadoTrabajosDao.findById(new Long(1));
        EstadoTrabajo estadoAbierto = estadoOpt.get();

        Trabajo trabajo = new Trabajo();
        trabajo.setVehiculo(veh.get());
        trabajo.setNombre(trabajoDto.getNombre());
        trabajo.setDescripcion(trabajoDto.getDescripcion());
        trabajo.setFechaCreado(LocalDateTime.now());
        trabajo.setEstado(estadoAbierto);
        trabajo.setPeritado(trabajoDto.getPeritado());
        trabajoDao.save(trabajo);

        return trabajo;
    }

    @Override
    public Block<Asistencia> findAllAsistencias(int page, int size) {
        Slice<Asistencia> asistencias = asistenciaDao.findAll(PageRequest.of(page, size));

        return new Block<>(asistencias.getContent(), asistencias.hasNext());
    }

    @Override
    public List<Asistencia> findAllAsistenciasPorFecha(String fecha) {

        String[] fecha_tabla = fecha.split("-");

        LocalDateTime fechaFiltrado = LocalDateTime.of(Integer.valueOf(fecha_tabla[0]), Integer.valueOf(fecha_tabla[1]),
                Integer.valueOf(fecha_tabla[2]), 0, 0, 0, 0);

        List<Asistencia> asistencias = new ArrayList<>(101);
        asistencias = asistenciaDao.findAsistenciasPorFecha(fechaFiltrado);

        // Las asistencias se pasarán a un array en donde estén ordenadas por franja horaria
        // ** -> Las asistencias se repiten si están en varias franjas horarias <-- **

        List<Asistencia> resultado = new ArrayList<>();
        resultado = ordenarAsistenciasParaTabla(asistencias);

        return resultado;
    }



    @Override
    public Slice<Trabajo> getTrabajosAbiertos() {
        return trabajoDao.findByEstado("Abierto");
    }

    @Override
    public Slice<PuestoTaller> getElevadores() {
        return puestoTallerDao.findAll(PageRequest.of(0,10));
    }

    @Override
    public Slice<Trabajo> getTrabajosOrderByFecha(int page, int size) {
        return trabajoDao.findTrabajosOrderByFecha(PageRequest.of(page, size));
    }

    @Override
    public Slice<Asistencia> getAsistenciasOrderByFecha(Long idTrabajo, int page, int size) {
        return trabajoDao.findAsistenciasOrderByFecha(idTrabajo, PageRequest.of(page, size));
    }

    @Override
    public void cambiarEstadoTrabajo(Long idTrabajo, String estado) throws InstanceNotFoundException {
        Optional<Trabajo> trabajoOpt = trabajoDao.findById(idTrabajo);
        if (trabajoOpt.isEmpty())
            throw new InstanceNotFoundException("trabajo.idTrabajo", idTrabajo);
        Optional<EstadoTrabajo> estadoOpt = estadoTrabajosDao.findByNombre(estado.replace("\"", ""));
        if (estadoOpt.isEmpty())
            throw new InstanceNotFoundException("estadoTrabajo.nombreEstado", estado);

        Trabajo trabajo = trabajoOpt.get();
        trabajo.setEstado(estadoOpt.get());
        trabajoDao.save(trabajo);
    }

    @Override
    public Trabajo getTrabajoByID(Long idTrabajo) throws InstanceNotFoundException{
        Optional<Trabajo> trabajoOpt = trabajoDao.findById(idTrabajo);
        if (trabajoOpt.isEmpty())
            throw new InstanceNotFoundException("entidades.trabajo.idTrabajo", idTrabajo);
        return trabajoOpt.get();
    }

    @Override
    public Asistencia getAsistenciaByID(Long idAsistencia) throws InstanceNotFoundException{
        Optional<Asistencia> asistenciaOpt = asistenciaDao.findById(idAsistencia);
        if (asistenciaOpt.isEmpty())
            throw new InstanceNotFoundException("entidades.asistencia.idAsistencia", idAsistencia);
        return asistenciaOpt.get();
    }

    // ** -> Las asistencias se repiten si están en varias franjas horarias <-- **
    // Se ordenan tal u como quedarían en la tabla
    private List<Asistencia> ordenarAsistenciasParaTabla(List<Asistencia> asistencias){
        final int SLOTS_HORARIOS = 500;
        Asistencia[] resultado = new Asistencia[SLOTS_HORARIOS];

        // Inicializacion de ArrayList para que tenga tamaño 100
        for(int i = 0; i < SLOTS_HORARIOS; i++)
        {
            resultado[i] =  null;
        }

        for (Asistencia a : asistencias) {
            for (Horarios h:a.getHorarios()) {
                resultado[calcularIndiceInsercion(
                        a.getPuesto().getIdPuesto().intValue(), h.getIdFranjaHoraria().intValue())] = a;
            }
        }

        return Arrays.asList(resultado);
    }

    public List<PiezasAsistenciasDto> getNumeroUnidadesPiezaAsistencia(List<PiezasAsistenciasDto> asistenciaPiezasDto, Long idAsistencia){
        for (PiezasAsistenciasDto paDto: asistenciaPiezasDto) {
            Long idPieza = paDto.getIdPieza();
            Optional<AsistenciaPieza> apOpt = asistenciaPiezaDao.findByIdPiezaIdAsistencia(idPieza, idAsistencia);
            if(apOpt.isPresent()){
                Long numUnidades = apOpt.get().getNumeroUnidades();
                paDto.setNumeroUnidades(numUnidades);
                paDto.setIdAsistencia(idAsistencia);
            }
        }

        return asistenciaPiezasDto;
    }

    @Override
    public List<PiezasAsistenciasDto> getAllPiezas() {
        List<Pieza> piezas = (List<Pieza>) piezaDao.findAll();
        List<PiezasAsistenciasDto> resultado = new ArrayList<>();
        for (Pieza p : piezas) {
            PiezasAsistenciasDto paDto = new PiezasAsistenciasDto();
            paDto.setIdPieza(p.getIdPieza());
            paDto.setNombre(p.getNombre());
            resultado.add(paDto);
        }
        return resultado;
    }

    @Override
    public void cambiarRetraso(Long idAsistencia, String motivo) throws InstanceNotFoundException, CampoVacioException {
        Optional<Asistencia> asOpt = asistenciaDao.findById(idAsistencia);
        if (asOpt.isEmpty())
            throw new InstanceNotFoundException("asistencias.idAsistencia", idAsistencia);
        if (motivo.equals(""))
            throw new CampoVacioException("asistencia.motivoRetraso", motivo);

        Asistencia asistencia = asOpt.get();
        if(!motivo.equals("\"null\"")){
            asistencia.setMotivoRetraso(motivo);
            asistencia.setRetrasada(true);
        }else{
            asistencia.setRetrasada(false);
        }
        asistenciaDao.save(asistencia);
    }

    @Override
    public Slice<Asistencia> getAsistenciasRetrasadas(int page, int size) {
        return asistenciaDao.findRetrasadas(PageRequest.of(page, size));
    }

    private int calcularIndiceInsercion(int elevador, int idranjaHoraria){
        int resultado = 0;

        switch(elevador){
            case 1 :    resultado = idranjaHoraria;
            break;
            case 2 :    resultado = 20 + idranjaHoraria;
            break;
            case 3 :    resultado = 40 + idranjaHoraria;
            break;
            case 4 :    resultado = 60 + idranjaHoraria;
            break;
            case 5 :    resultado = 80 + idranjaHoraria;
            break;
        }

        return resultado;
    }
}
