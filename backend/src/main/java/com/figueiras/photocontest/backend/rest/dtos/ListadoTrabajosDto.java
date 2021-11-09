package com.figueiras.photocontest.backend.rest.dtos;

public class ListadoTrabajosDto {
    private String matricula;
    private String estado;
    private Long idCliente;
    private String nombreCliente;
    private String fecha;

    public ListadoTrabajosDto(String matricula, String estado, Long idCliente, String nombreCliente, String fecha) {
        this.matricula = matricula;
        this.estado = estado;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.fecha = fecha;
    }
}
