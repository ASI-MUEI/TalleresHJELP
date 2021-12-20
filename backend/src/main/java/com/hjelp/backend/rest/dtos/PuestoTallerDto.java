package com.hjelp.backend.rest.dtos;

public class PuestoTallerDto {
    private Long idPuestoTaller;
    private String nombre;
    private String descripcion;

    public Long getIdPuestoTaller() {
        return idPuestoTaller;
    }

    public void setIdPuestoTaller(Long idPuestoTaller) {
        this.idPuestoTaller = idPuestoTaller;
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
