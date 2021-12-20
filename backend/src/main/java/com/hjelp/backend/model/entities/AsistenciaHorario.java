package com.hjelp.backend.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AsistenciaHorario {
    private Long idAsistenciaHorario;
    private Long idAsistencia;
    private Long idHorario;

    public AsistenciaHorario() {
    }

    public AsistenciaHorario(Long idAsistencia, Long idHorario) {
        this.idAsistencia = idAsistencia;
        this.idHorario = idHorario;
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

    public Long getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Long idHorario) {
        this.idHorario = idHorario;
    }
}


