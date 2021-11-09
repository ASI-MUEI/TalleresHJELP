package com.figueiras.photocontest.backend.rest.dtos;

import java.util.List;

public class AsistenciaCompletaDto {
    private Long idAsistencia;
    private Long elevador;
    private List<MecanicoAsistenciaDto> mecanicos;
    private List<HorariosAsistenciasDto> horarios;
    private String fecha;
    private Long idTrabajo;
    private Float precio;
    private Long duracionEstimada;
    private Boolean peritaje;
    private String descripcion;

    public AsistenciaCompletaDto(Long idAsistencia, Long elevador, List<MecanicoAsistenciaDto> mecanicos, List<HorariosAsistenciasDto> horarios, String fecha, Long idTrabajo, Float precio, Long duracionEstimada, Boolean peritaje, String descripcion) {
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
    }
}
