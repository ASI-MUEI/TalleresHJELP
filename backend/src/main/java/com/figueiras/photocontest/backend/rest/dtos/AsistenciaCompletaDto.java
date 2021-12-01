package com.figueiras.photocontest.backend.rest.dtos;

import java.util.List;

public class AsistenciaCompletaDto {
    private Long idAsistencia;
    private Long elevador;
    private String nombreElevador;
    private List<MecanicoAsistenciaDto> mecanicos;
    private List<HorariosAsistenciasDto> horarios;
    private Long fecha;
    private Long idTrabajo;
    private Float precio;
    private Long duracionEstimada;
    private Boolean peritaje;
    private String descripcion;
    private String matricula;
    private String nombreCliente;
    private Long idCliente;
    private List<PiezasAsistenciasDto> piezasReparacion;
    private String modeloDeVehiculo;
    private String manualVehiculo;
    private String tipoReparacion;
    private Boolean retrasada;
    private String motivoRetraso;


    public AsistenciaCompletaDto() {}

    public AsistenciaCompletaDto(Long idAsistencia, Long elevador, List<MecanicoAsistenciaDto> mecanicos, List<HorariosAsistenciasDto> horarios, Long fecha, Long idTrabajo, Float precio, Long duracionEstimada, Boolean peritaje, String descripcion, String matricula, String nombreCliente, Long idCliente, List<PiezasAsistenciasDto> piezasReparacion, String motivoRetraso) {
        this.idAsistencia = idAsistencia;
        this.elevador = elevador;
        this.mecanicos = mecanicos;
        this.horarios = horarios;
        this.fecha = fecha;
        this.idTrabajo = idTrabajo;
        this.precio = precio;
        this.duracionEstimada = duracionEstimada;
        this.peritaje = peritaje;
        this.descripcion = descripcion;
        this.matricula = matricula;
        this.nombreCliente = nombreCliente;
        this.idCliente = idCliente;
        this.piezasReparacion = piezasReparacion;
        this.motivoRetraso = motivoRetraso;
    }

    public Long getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Long idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Long getElevador() {
        return elevador;
    }

    public void setElevador(Long elevador) {
        this.elevador = elevador;
    }

    public List<MecanicoAsistenciaDto> getMecanicos() {
        return mecanicos;
    }

    public void setMecanicos(List<MecanicoAsistenciaDto> mecanicos) {
        this.mecanicos = mecanicos;
    }

    public List<HorariosAsistenciasDto> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorariosAsistenciasDto> horarios) {
        this.horarios = horarios;
    }

    public Long getFecha() {
        return fecha;
    }

    public void setFecha(Long fecha) {
        this.fecha = fecha;
    }

    public Long getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(Long idTrabajo) {
        this.idTrabajo = idTrabajo;
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

    public Boolean getPeritaje() {
        return peritaje;
    }

    public void setPeritaje(Boolean peritaje) {
        this.peritaje = peritaje;
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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public List<PiezasAsistenciasDto> getPiezasReparacion() {
        return piezasReparacion;
    }

    public void setPiezasReparacion(List<PiezasAsistenciasDto> piezasReparacion) {
        this.piezasReparacion = piezasReparacion;
    }

    public String getNombreElevador() {
        return nombreElevador;
    }

    public void setNombreElevador(String nombreElevador) {
        this.nombreElevador = nombreElevador;
    }

    public String getModeloDeVehiculo() {
        return modeloDeVehiculo;
    }

    public void setModeloDeVehiculo(String modeloDeVehiculo) {
        this.modeloDeVehiculo = modeloDeVehiculo;
    }

    public String getManualVehiculo() {
        return manualVehiculo;
    }

    public void setManualVehiculo(String manualVehiculo) {
        this.manualVehiculo = manualVehiculo;
    }

    public String getTipoReparacion() {
        return tipoReparacion;
    }

    public void setTipoReparacion(String tipoReparacion) {
        this.tipoReparacion = tipoReparacion;
    }

    public Boolean getRetrasada() {
        return retrasada;
    }

    public void setRetrasada(Boolean retrasada) {
        this.retrasada = retrasada;
    }

    public String getMotivoRetraso() {
        return motivoRetraso;
    }

    public void setMotivoRetraso(String motivoRetraso) {
        this.motivoRetraso = motivoRetraso;
    }
}
