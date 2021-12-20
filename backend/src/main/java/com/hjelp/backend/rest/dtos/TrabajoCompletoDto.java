package com.hjelp.backend.rest.dtos;


public class TrabajoCompletoDto {
    private Long idTrabajo;
    private String nombre;
    private String descripcion;
    private String matricula;
    private String nombreCliente;
    private String estado;
    private Long fecha;
    private Boolean peritaje;

    public TrabajoCompletoDto() {
    }

    public TrabajoCompletoDto(Long idTrabajo, String nombre, String descripcion, String matricula, String nombreCliente, String estado, Long fecha, Boolean peritaje) {
        this.idTrabajo = idTrabajo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.matricula = matricula;
        this.nombreCliente = nombreCliente;
        this.estado = estado;
        this.fecha = fecha;
        this.peritaje = peritaje;
    }

    public Boolean getPeritaje() {
        return peritaje;
    }

    public void setPeritaje(Boolean peritaje) {
        this.peritaje = peritaje;
    }

    public Long getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(Long idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getFecha() {
        return fecha;
    }

    public void setFecha(Long fecha) {
        this.fecha = fecha;
    }
}
