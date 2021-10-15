package com.figueiras.photocontest.backend.rest.dtos;

import java.util.Map;

public class AsistenciaFranjaHorariaDto {
    private Long idAsistencia;
    private Map<Long, String> franjasHorarias;

    public Long getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Long idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Map<Long, String> getFranjasHorarias() {
        return franjasHorarias;
    }

    public void setFranjasHorarias(Map<Long, String> franjasHorarias) {
        this.franjasHorarias = franjasHorarias;
    }
}
