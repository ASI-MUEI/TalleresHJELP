package com.figueiras.photocontest.backend.rest.conversor;

import com.figueiras.photocontest.backend.model.entities.Asistencia;
import com.figueiras.photocontest.backend.model.entities.Usuario;
import com.figueiras.photocontest.backend.rest.dtos.AsistenciasDto;
import com.figueiras.photocontest.backend.rest.dtos.MecanicoDto;

import java.util.ArrayList;
import java.util.List;

public class AsistenciaConversor {

    public static AsistenciasDto toAsistenciasDto(Asistencia asistencia){
        AsistenciasDto resultado = new AsistenciasDto();
        List<MecanicoDto> mecanicosDto = new ArrayList<>();

        resultado.setMatricula(asistencia.getTrabajo().getVehiculo().getMatricula());
        resultado.setEstado(asistencia.getEstado().getIdEstado());
        resultado.setFecha(asistencia.getFecha().toString());
        resultado.setIdTrabajo(asistencia.getTrabajo().getIdTrabajo());
        resultado.setPuestoTaller(asistencia.getPuesto().getIdPuestoTaller());
        List<Usuario> mecanicos = asistencia.getMecanicos();
        int numMecanicos = mecanicos.size();
        for (int i = 0; i<numMecanicos; i++) {
            MecanicoDto mecanicoDto = new MecanicoDto();
            mecanicoDto.setIdMecanico(mecanicos.get(i).getIdUsuario());
            mecanicoDto.setNombreMecanico(mecanicos.get(i).getNombreUsuario());
            mecanicosDto.add(mecanicoDto);
        }
        resultado.setMecanicos(mecanicosDto);
        resultado.setTipo(asistencia.getTipo().getIdTipo());
        resultado.setPeritaje(asistencia.getPeritaje());
        resultado.setDuracionEstimada(asistencia.getDuracionEstimada());
        resultado.setDescripcion(asistencia.getDescripcion());
        resultado.setPrecio(asistencia.getPrecio());

        return resultado;
    }
}
