package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PuestoTaller {
    private Long idPuesto;
    private String nombre;
    private String descripcion;

    public PuestoTaller() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Long idMarca) {
        this.idPuesto = idMarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
