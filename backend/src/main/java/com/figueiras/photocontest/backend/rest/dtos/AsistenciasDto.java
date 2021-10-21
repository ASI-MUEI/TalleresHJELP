package com.figueiras.photocontest.backend.rest.dtos;

public class AsistenciasDto {
    private Long tipo;
    private String fecha;
    private Long idVehiculo;
    private Long mecanico;
    private Long estado;
    private Long puestoTaller;

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

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
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
}
