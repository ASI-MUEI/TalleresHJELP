package com.figueiras.photocontest.backend.rest.dtos;

import java.util.List;

public class ListarReparacionesDto {
    private Long idReparacion;
    private String fecha;
    private Long duracionEstimada;
    private Long idElevador;
    private String nombreElevador;
    private Float precio;
    private List<MecanicoAsistenciaDto> mecanicos;
    private String descripción;

    public ListarReparacionesDto(Long idReparacion, String fecha, Long duracionEstimada, Long idElevador,
                                 String nombreElevador, Float precio, List<MecanicoAsistenciaDto> mecanicos,
                                 String descripción) {
        this.idReparacion = idReparacion;
        this.fecha = fecha;
        this.duracionEstimada = duracionEstimada;
        this.idElevador = idElevador;
        this.nombreElevador = nombreElevador;
        this.precio = precio;
        this.mecanicos = mecanicos;
        this.descripción = descripción;
    }
}
