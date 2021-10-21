package com.figueiras.photocontest.backend.rest.dtos;

public class AsistenciaPuestoTDto {
    private Long idPuesto;
    private Long idAsistencia;

    public Long getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Long idPuesto) {
        this.idPuesto = idPuesto;
    }

    public Long getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Long idAsistencia) {
        this.idAsistencia = idAsistencia;
    }
}
