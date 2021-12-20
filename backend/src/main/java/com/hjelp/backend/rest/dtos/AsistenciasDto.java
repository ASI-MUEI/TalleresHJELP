package com.hjelp.backend.rest.dtos;

import java.util.List;

public class AsistenciasDto {
    private Long idAsistencia;
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
    private Long tipo;
    private String nombreTipo;
    private List<PiezasAsistenciasDto> piezasReparacion;

    public Long getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Long idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

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

    public Long getElevador() {
        return elevador;
    }

    public void setElevador(Long elevador) {
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

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public List<PiezasAsistenciasDto> getPiezasReparacion() {
        return piezasReparacion;
    }

    public void setPiezasReparacion(List<PiezasAsistenciasDto> piezasReparacion) {
        this.piezasReparacion = piezasReparacion;
    }
}


