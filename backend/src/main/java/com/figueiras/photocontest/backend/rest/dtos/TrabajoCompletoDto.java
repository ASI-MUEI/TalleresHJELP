package com.figueiras.photocontest.backend.rest.dtos;


public class TrabajoCompletoDto {
    private Long idTrabajo;
    private String nombre;
    private String descripcion;
    private String matricula;
    private String nombreCliente;
    private String estado;
    private String fecha;

    public TrabajoCompletoDto(Long idTrabajo, String nombre, String descripcion, String matricula, String nombreCliente, String estado, String fecha) {
        this.idTrabajo = idTrabajo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.matricula = matricula;
        this.nombreCliente = nombreCliente;
        this.estado = estado;
        this.fecha = fecha;
    }
}
