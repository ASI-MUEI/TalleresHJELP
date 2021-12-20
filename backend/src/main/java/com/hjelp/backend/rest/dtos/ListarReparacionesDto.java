package com.hjelp.backend.rest.dtos;

import java.util.List;

public class ListarReparacionesDto {
    private Long idReparacion;
    private Long fecha;
    private Long duracionEstimada;
    private Long idElevador;
    private String nombreElevador;
    private Float precio;
    private List<MecanicoAsistenciaDto> mecanicos;
    private String descripción;
    private List<PiezasAsistenciasDto> piezasReparacion;

    public ListarReparacionesDto() {
    }

    public ListarReparacionesDto(Long idReparacion, Long fecha, Long duracionEstimada, Long idElevador, String nombreElevador, Float precio, List<MecanicoAsistenciaDto> mecanicos, String descripción, List<PiezasAsistenciasDto> piezasReparacion) {
        this.idReparacion = idReparacion;
        this.fecha = fecha;
        this.duracionEstimada = duracionEstimada;
        this.idElevador = idElevador;
        this.nombreElevador = nombreElevador;
        this.precio = precio;
        this.mecanicos = mecanicos;
        this.descripción = descripción;
        this.piezasReparacion = piezasReparacion;
    }

    public Long getIdReparacion() {
        return idReparacion;
    }

    public void setIdReparacion(Long idReparacion) {
        this.idReparacion = idReparacion;
    }

    public Long getFecha() {
        return fecha;
    }

    public void setFecha(Long fecha) {
        this.fecha = fecha;
    }

    public Long getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(Long duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    public Long getIdElevador() {
        return idElevador;
    }

    public void setIdElevador(Long idElevador) {
        this.idElevador = idElevador;
    }

    public String getNombreElevador() {
        return nombreElevador;
    }

    public void setNombreElevador(String nombreElevador) {
        this.nombreElevador = nombreElevador;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public List<MecanicoAsistenciaDto> getMecanicos() {
        return mecanicos;
    }

    public void setMecanicos(List<MecanicoAsistenciaDto> mecanicos) {
        this.mecanicos = mecanicos;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public List<PiezasAsistenciasDto> getPiezasReparacion() {
        return piezasReparacion;
    }

    public void setPiezasReparacion(List<PiezasAsistenciasDto> piezasReparacion) {
        this.piezasReparacion = piezasReparacion;
    }
}
