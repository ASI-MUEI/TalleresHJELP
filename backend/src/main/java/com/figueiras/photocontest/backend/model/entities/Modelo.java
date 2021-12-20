package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.*;

@Entity
public class Modelo {

    private Long idModelo;
    private String nombre;
    private String descripcion;
    private Marca marca;
    private String manual;

    public Modelo(Long idModelo, String nombre, String descripcion, Marca marca, String manual) {
        this.idModelo = idModelo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.manual = manual;
    }

    public Modelo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Long idModelo) {
        this.idModelo = idModelo;
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

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "idMarca")
    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

}
