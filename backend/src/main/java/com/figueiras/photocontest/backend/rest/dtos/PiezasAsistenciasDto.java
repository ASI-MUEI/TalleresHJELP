package com.figueiras.photocontest.backend.rest.dtos;

public class PiezasAsistenciasDto {
    private Long idPieza;
    private Long idAsistencia;
    private String nombre;
    private String manual;
    private Float precio;
    private Long numeroUnidades;

    public PiezasAsistenciasDto() {
    }

    public PiezasAsistenciasDto(Long idPieza, Long idAsistencia, String nombre, String manual, Float precio) {
        this.idPieza = idPieza;
        this.idAsistencia = idAsistencia;
        this.nombre = nombre;
        this.manual = manual;
        this.precio = precio;
    }

    public PiezasAsistenciasDto(Long idPieza, String nombre, String manual, Float precio) {
        this.idPieza = idPieza;
        this.nombre = nombre;
        this.manual = manual;
        this.precio = precio;
    }

    public Long getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(Long idPieza) {
        this.idPieza = idPieza;
    }

    public Long getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Long idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Long getNumeroUnidades() {
        return numeroUnidades;
    }

    public void setNumeroUnidades(Long numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }
}
