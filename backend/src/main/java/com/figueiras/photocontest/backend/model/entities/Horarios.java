package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Horarios {
    private Long idFranjaHoraria;
    private String franjaHoraria;

    public Horarios() {
    }

    public Horarios(Long idFranjaHoraria, String franjaHoraria) {
        this.idFranjaHoraria = idFranjaHoraria;
        this.franjaHoraria = franjaHoraria;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdFranjaHoraria() {
        return idFranjaHoraria;
    }

    public void setIdFranjaHoraria(Long idFranjaHoraria) {
        this.idFranjaHoraria = idFranjaHoraria;
    }

    public String getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(String franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }
}
