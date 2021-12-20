package com.hjelp.backend.rest.dtos;


public class VehiculoDto {
    private Long idVehiculo;
    private Long usuario;
    private String numBastidor;
    private String matricula;
    private Long marca;
    private Long modelo;
    private Long flota;

    public VehiculoDto() {
    }

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public String getNumBastidor() {
        return numBastidor;
    }

    public void setNumBastidor(String numBastidor) {
        this.numBastidor = numBastidor;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Long getMarca() {
        return marca;
    }

    public void setMarca(Long marca) {
        this.marca = marca;
    }

    public Long getModelo() {
        return modelo;
    }

    public void setModelo(Long modelo) {
        this.modelo = modelo;
    }

    public Long getFlota() {
        return flota;
    }

    public void setFlota(Long flota) {
        this.flota = flota;
    }
}
