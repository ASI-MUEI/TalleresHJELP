package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Asistencia {
    private Long idAsistencia;
    private PuestoTaller puesto;
    private TipoAsistencias tipo;
    private Usuario mecanico;
    private EstadoAsistencias estado;
    private Vehiculo vehiculo;
    private Date fecha;

    public Asistencia() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Long idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idPuesto")
    public PuestoTaller getPuesto() {
        return puesto;
    }

    public void setPuesto(PuestoTaller puesto) {
        this.puesto = puesto;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipo")
    public TipoAsistencias getTipo() {
        return tipo;
    }

    public void setTipo(TipoAsistencias tipo) {
        this.tipo = tipo;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
    public Usuario getMecanico() {
        return mecanico;
    }

    public void setMecanico(Usuario mecanico) {
        this.mecanico = mecanico;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstado")
    public EstadoAsistencias getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsistencias estado) {
        this.estado = estado;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idVechiculo")
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
