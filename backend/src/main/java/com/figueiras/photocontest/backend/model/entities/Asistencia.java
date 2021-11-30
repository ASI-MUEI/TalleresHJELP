package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Asistencia {
    private Long idAsistencia;
    private PuestoTaller puesto;
    private TipoAsistencias tipo;
    private List<Usuario> mecanicos;
    private List<Horarios> horarios;
    private LocalDateTime fecha;
    private Trabajo trabajo;
    private Float precio;
    private Long duracionEstimada;
    private Boolean peritaje;
    private String descripcion;
    private List<Pieza> piezas;
    private Boolean retrasada;
    private String motivoRetraso;

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

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "idPuesto")
    public PuestoTaller getPuesto() {
        return puesto;
    }

    public void setPuesto(PuestoTaller puesto) {
        this.puesto = puesto;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "idTipo")
    public TipoAsistencias getTipo() {
        return tipo;
    }

    public void setTipo(TipoAsistencias tipo) {
        this.tipo = tipo;
    }

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "AsistenciaMecanico",
            joinColumns = { @JoinColumn(name = "idAsistencia") },
            inverseJoinColumns = { @JoinColumn(name = "idMecanico") }
    )
    public List<Usuario> getMecanicos() {
        return mecanicos;
    }

    public void setMecanicos(List<Usuario> mecanicos) {
        this.mecanicos = mecanicos;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "AsistenciaPieza",
            joinColumns = { @JoinColumn(name = "idAsistencia") },
            inverseJoinColumns = { @JoinColumn(name = "idPieza") }
    )
    public List<Pieza> getPiezas() {
        return piezas;
    }

    public void setPiezas(List<Pieza> piezas) {
        this.piezas = piezas;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "AsistenciaHorario",
            joinColumns = { @JoinColumn(name = "idAsistencia") },
            inverseJoinColumns = { @JoinColumn(name = "idHorario") }
    )
    public List<Horarios> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horarios> horarios) {
        this.horarios = horarios;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idTrabajo")
    public Trabajo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Long getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(Long duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    public Boolean getPeritaje() {
        return peritaje;
    }

    public void setPeritaje(Boolean peritaje) {
        this.peritaje = peritaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getRetrasada() {
        return retrasada;
    }

    public void setRetrasada(Boolean retrasada) {
        this.retrasada = retrasada;
    }

    public String getMotivoRetraso() {
        return motivoRetraso;
    }

    public void setMotivoRetraso(String motivoRetraso) {
        this.motivoRetraso = motivoRetraso;
    }
}
