package com.hjelp.backend.rest.dtos;

public class FlotaDto {
    private Long idFlota;
    private String nombre;
    private String descripcion;

    public Long getIdFlota() {
        return idFlota;
    }

    public void setIdFlota(Long idFlota) {
        this.idFlota = idFlota;
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
