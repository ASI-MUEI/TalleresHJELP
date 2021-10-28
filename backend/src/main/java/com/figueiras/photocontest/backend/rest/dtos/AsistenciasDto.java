package com.figueiras.photocontest.backend.rest.dtos;

public class AsistenciasDto {
    private Long tipo;
    private String fecha;
    private Long mecanico;
    private Long estado;
    private Long puestoTaller;
    private Long idTrabajo;

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

    public Long getMecanico() {
        return mecanico;
    }

    public void setMecanico(Long mecanico) {
        this.mecanico = mecanico;
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
}
