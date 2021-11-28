package com.figueiras.photocontest.backend.rest.dtos;

public class TrabajoDto {
    private String nombre;
    private String descripcion;
    private String matricula;
    private Boolean peritado;

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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Boolean getPeritado() {
        return peritado;
    }

    public void setPeritado(Boolean peritado) {
        this.peritado = peritado;
    }
}
