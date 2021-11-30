package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AsistenciaPieza {
    private Long idAsistenciaPieza;
    private Long idAsistencia;
    private Long idPieza;
    private Long numeroUnidades;

    public AsistenciaPieza() {
    }

    public AsistenciaPieza(Long idAsistencia, Long idPieza) {
        this.idAsistencia = idAsistencia;
        this.idPieza = idPieza;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdAsistenciaPieza() {
        return idAsistenciaPieza;
    }

    public void setIdAsistenciaPieza(Long idAsistenciaPieza) {
        this.idAsistenciaPieza = idAsistenciaPieza;
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

    public Long getNumeroUnidades() {
        return numeroUnidades;
    }

    public void setNumeroUnidades(Long numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }
}


