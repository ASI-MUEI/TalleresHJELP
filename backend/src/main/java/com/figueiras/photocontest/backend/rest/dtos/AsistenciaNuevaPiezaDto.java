package com.figueiras.photocontest.backend.rest.dtos;

public class AsistenciaNuevaPiezaDto {
    private Long idAsistencia;
    private Long idPieza;

    public AsistenciaNuevaPiezaDto(Long idAsistencia, Long idPieza) {
        this.idAsistencia = idAsistencia;
        this.idPieza = idPieza;
    }

    public Long getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Long idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Long getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(Long idPieza) {
        this.idPieza = idPieza;
    }
}
