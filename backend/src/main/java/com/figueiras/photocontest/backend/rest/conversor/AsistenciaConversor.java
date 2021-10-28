package com.figueiras.photocontest.backend.rest.conversor;

import com.figueiras.photocontest.backend.model.entities.Asistencia;
import com.figueiras.photocontest.backend.rest.dtos.AsistenciasDto;

public class AsistenciaConversor {

    public static AsistenciasDto toAsistenciasDto(Asistencia asistencia){
        AsistenciasDto resultado = new AsistenciasDto();

        resultado.setMatricula(asistencia.getTrabajo().getVehiculo().getMatricula());
        resultado.setEstado(asistencia.getEstado().getIdEstado());
        resultado.setFecha(asistencia.getFecha().toString());
        resultado.setIdTrabajo(asistencia.getTrabajo().getIdTrabajo());
        resultado.setPuestoTaller(asistencia.getPuesto().getIdPuestoTaller());
        resultado.setMecanico(asistencia.getMecanico().getIdUsuario());
        resultado.setTipo(asistencia.getTipo().getIdTipo());

        return resultado;
    }
}
