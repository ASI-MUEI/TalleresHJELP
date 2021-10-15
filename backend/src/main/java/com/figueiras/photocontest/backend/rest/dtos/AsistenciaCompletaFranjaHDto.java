package com.figueiras.photocontest.backend.rest.dtos;


import java.util.List;


public class AsistenciaCompletaFranjaHDto {

    private Long idAsistencia;
    private PuestoTallerDto puesto;
    private TipoAsistenciasDto tipo;
    private UsuarioDto mecanico;
    private EstadoAsistenciasDto estado;
    private VehiculoDto vehiculo;
    private String fecha;
    private List<FranjaHorariaDto> franjasHorarias;

    public Long getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Long idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public PuestoTallerDto getPuesto() {
        return puesto;
    }

    public void setPuesto(PuestoTallerDto puesto) {
        this.puesto = puesto;
    }

    public TipoAsistenciasDto getTipo() {
        return tipo;
    }

    public void setTipo(TipoAsistenciasDto tipo) {
        this.tipo = tipo;
    }

    public UsuarioDto getMecanico() {
        return mecanico;
    }

    public void setMecanico(UsuarioDto mecanico) {
        this.mecanico = mecanico;
    }

    public EstadoAsistenciasDto getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsistenciasDto estado) {
        this.estado = estado;
    }

    public VehiculoDto getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoDto vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<FranjaHorariaDto> getFranjasHorarias() {
        return franjasHorarias;
    }

    public void setFranjasHorarias(List<FranjaHorariaDto> franjasHorarias) {
        this.franjasHorarias = franjasHorarias;
    }
}
