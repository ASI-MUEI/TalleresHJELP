package com.figueiras.photocontest.backend.rest.dtos;

public class TrabajoDto {
    private String nombre;
    private String descripcion;
    private Long vehiculo;
    private String matricula;

    public TrabajoDto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public Long getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Long vehiculo) {
        this.vehiculo = vehiculo;
    }
}
