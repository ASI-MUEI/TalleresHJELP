package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AsistenciaPieza {
    private Long idAsistenciaHorario;
    private Long idAsistencia;
    private Long idPieza;

    public AsistenciaPieza() {
    }

    public AsistenciaPieza(Long idAsistencia, Long idPieza) {
        this.idAsistencia = idAsistencia;
        this.idPieza = idPieza;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdAsistenciaHorario() {
        return idAsistenciaHorario;
    }

    public void setIdAsistenciaHorario(Long idAsistenciaHorario) {
        this.idAsistenciaHorario = idAsistenciaHorario;
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

    public void setIdPieza(Long idHorario) {
        this.idPieza = idHorario;
    }
}


