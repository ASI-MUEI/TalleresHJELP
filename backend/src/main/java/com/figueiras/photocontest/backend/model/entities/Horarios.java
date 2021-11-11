package com.figueiras.photocontest.backend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.List;

@Entity
public class Horarios {
    private Long idFranjaHoraria;
    private String franjaHoraria;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Asistencia> asistencias;

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

    @ManyToMany(mappedBy = "mecanicos", fetch = FetchType.EAGER)
    public List<Asistencia> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }
}
