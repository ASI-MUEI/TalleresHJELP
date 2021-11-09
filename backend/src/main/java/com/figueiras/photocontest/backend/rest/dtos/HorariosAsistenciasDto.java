package com.figueiras.photocontest.backend.rest.dtos;

public class HorariosAsistenciasDto {
    private Long id;
    private String nombre;

    public HorariosAsistenciasDto() {
    }

    public HorariosAsistenciasDto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
