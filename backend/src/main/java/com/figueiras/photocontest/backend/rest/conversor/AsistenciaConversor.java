package com.figueiras.photocontest.backend.rest.conversor;

import com.figueiras.photocontest.backend.model.entities.Asistencia;
import com.figueiras.photocontest.backend.model.entities.Horarios;
import com.figueiras.photocontest.backend.model.entities.Usuario;
import com.figueiras.photocontest.backend.rest.dtos.*;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.List;

public class AsistenciaConversor {

    public static AsistenciasDto toAsistenciasDto(Asistencia asistencia){
        AsistenciasDto resultado = new AsistenciasDto();
        List<MecanicoAsistenciaDto> mecanicosDto = new ArrayList<>();

        resultado.setMatricula(asistencia.getTrabajo().getVehiculo().getMatricula());
        //resultado.setEstado(asistencia.getEstado().getIdEstado());
        resultado.setFecha(asistencia.getFecha().toString());
        resultado.setIdTrabajo(asistencia.getTrabajo().getIdTrabajo());
        resultado.setPuestoTaller(asistencia.getPuesto().getIdPuestoTaller());
        List<Usuario> mecanicos = asistencia.getMecanicos();
        int numMecanicos = mecanicos.size();
        for (int i = 0; i<numMecanicos; i++) {
            MecanicoAsistenciaDto mecanicoDto = new MecanicoAsistenciaDto();
            mecanicoDto.setIdMecanico(mecanicos.get(i).getIdUsuario());
            mecanicosDto.add(mecanicoDto);
        }
        resultado.setMecanicos(mecanicosDto);
        //resultado.setTipo(asistencia.getTipo().getIdTipo());
        resultado.setPeritaje(asistencia.getPeritaje());
        resultado.setDuracionEstimada(asistencia.getDuracionEstimada());
        resultado.setDescripcion(asistencia.getDescripcion());
        resultado.setPrecio(asistencia.getPrecio());

        return resultado;
    }

    public static List<ListarReparacionesDto> toListarAsistenciasDto(Slice<Asistencia> asistencias){
        List<ListarReparacionesDto> result = new ArrayList<>();
        for (Asistencia asistencia : asistencias){
            result.add(new ListarReparacionesDto(asistencia.getIdAsistencia(), asistencia.getFecha().toString(), asistencia.getDuracionEstimada(),
                    asistencia.getPuesto().getIdPuestoTaller(), asistencia.getPuesto().getNombre(), asistencia.getPrecio(), toMecanicosAsistenciaDto(asistencia.getMecanicos()), asistencia.getDescripcion()));
        }
        return  result;
    }

    private static List<MecanicoAsistenciaDto> toMecanicosAsistenciaDto(List<Usuario> usuarios){
        List<MecanicoAsistenciaDto> mecanicos = new ArrayList<>();
        for (Usuario usuario : usuarios){
            mecanicos.add(new MecanicoAsistenciaDto(usuario.getIdUsuario(), usuario.getNombreUsuario()));
        }
        return mecanicos;
    }

    private static List<HorariosAsistenciasDto> toHorariosAsistenciaDto(List<Horarios> horarios){
        List<HorariosAsistenciasDto> result = new ArrayList<>();
        for (Horarios hor : horarios){
            result.add(new HorariosAsistenciasDto(hor.getIdFranjaHoraria(), hor.getFranjaHoraria()));
        }
        return result;
    }

    public static AsistenciaCompletaDto toAsistenciaCompletaDto(Asistencia asistencia){
        return new AsistenciaCompletaDto(asistencia.getIdAsistencia(), asistencia.getPuesto().getIdPuestoTaller(),
                toMecanicosAsistenciaDto(asistencia.getMecanicos()), toHorariosAsistenciaDto(asistencia.getHorarios()),
                asistencia.getFecha().toString(), asistencia.getTrabajo().getIdTrabajo(), asistencia.getPrecio(),
                asistencia.getDuracionEstimada(), asistencia.getPeritaje(), asistencia.getDescripcion());
    }
}
