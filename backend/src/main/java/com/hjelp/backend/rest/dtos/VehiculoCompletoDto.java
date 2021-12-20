package com.hjelp.backend.rest.dtos;


public class VehiculoCompletoDto {
    private Long idVehiculo;
    private UsuarioDto usuario;
    private String numBastidor;
    private String matricula;
    private MarcaDto marca;
    private ModeloDto modelo;
    private FlotaDto flota;

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
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

    public MarcaDto getMarca() {
        return marca;
    }

    public void setMarca(MarcaDto marca) {
        this.marca = marca;
    }

    public ModeloDto getModelo() {
        return modelo;
    }

    public void setModelo(ModeloDto modelo) {
        this.modelo = modelo;
    }

    public FlotaDto getFlota() {
        return flota;
    }

    public void setFlota(FlotaDto flota) {
        this.flota = flota;
    }
}
