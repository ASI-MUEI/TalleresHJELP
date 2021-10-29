package com.figueiras.photocontest.backend.rest.dtos;

import java.util.List;

public class AsistenciasDto {
    private Long tipo;
    private String fecha;
    private List<MecanicoDto> mecanicos;
    private Long estado;
    private Long puestoTaller;
    private Long idTrabajo;
    private String matricula;
    private Float precio;
    private Long duracionEstimada;
    private String descripcion;
    private Boolean peritaje;

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<MecanicoDto> getMecanicos() {
        return mecanicos;
    }

    public void setMecanicos(List<MecanicoDto> mecanicos) {
        this.mecanicos = mecanicos;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }

    public Long getPuestoTaller() {
        return puestoTaller;
    }

    public void setPuestoTaller(Long puestoTaller) {
        this.puestoTaller = puestoTaller;
    }

    public Long getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(Long idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Long getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(Long duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getPeritaje() {
        return peritaje;
    }

    public void setPeritaje(Boolean peritaje) {
        this.peritaje = peritaje;
    }
}
