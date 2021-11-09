package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Trabajo {
    private Long idTrabajo;
    private String nombre;
    private String descripcion;
    private Vehiculo vehiculo;
    private EstadoTrabajo estado;
    private LocalDateTime fecha;

    public Trabajo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(Long idTrabajo) {
        this.idTrabajo = idTrabajo;
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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idVehiculo")
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstado")
    public EstadoTrabajo getEstado() {
        return estado;
    }

    public void setEstado(EstadoTrabajo estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
