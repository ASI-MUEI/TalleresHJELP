package com.figueiras.photocontest.backend.rest.dtos;

import java.util.List;

public class AsistenciasDto {
    private String fecha;
    private List<MecanicoAsistenciaDto> mecanicos;
    private Long elevador;
    private Long idTrabajo;
    private String matricula;
    private Float precio;
    private Long duracionEstimada;
    private String descripcion;
    private Boolean peritaje;
    private List<HorariosAsistenciasDto> horasDeTrabajo;


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<MecanicoAsistenciaDto> getMecanicos() {
        return mecanicos;
    }

    public void setMecanicos(List<MecanicoAsistenciaDto> mecanicos) {
        this.mecanicos = mecanicos;
    }

    public Long getPuestoTaller() {
        return elevador;
    }

    public void setPuestoTaller(Long elevador) {
        this.elevador = elevador;
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

    public List<HorariosAsistenciasDto> getHorasDeTrabajo() {
        return horasDeTrabajo;
    }

    public void setHorasDeTrabajo(List<HorariosAsistenciasDto> horasDeTrabajo) {
        this.horasDeTrabajo = horasDeTrabajo;
    }
}
