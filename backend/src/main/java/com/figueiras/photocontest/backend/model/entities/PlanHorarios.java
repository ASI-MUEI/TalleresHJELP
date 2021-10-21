package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.*;

@Entity
public class PlanHorarios {
    private Long idPlan;
    private Asistencia asistencia;
    private Horarios franjaHoraria;

    public PlanHorarios() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Long idPlan) {
        this.idPlan = idPlan;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idAsistencia")
    public Asistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idFranjaHoraria")
    public Horarios getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(Horarios franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }
}
