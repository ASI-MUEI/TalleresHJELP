package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.*;

@Entity
public class PuestoTallerVehiculo {
    private Vehiculo vehiculo;
    private PuestoTaller puesto;
    private Long idPuestoVehiculo;
    private String fechaComienzo;
    private String fechaFinal;

    public PuestoTallerVehiculo() {
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
    @JoinColumn(name = "idVechiculo")
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setIdPuestoVehiculo(Long idPuestoVehiculo) {
        this.idPuestoVehiculo = idPuestoVehiculo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdPuestoVehiculo() {
        return idPuestoVehiculo;
    }

    public String getFechaComienzo() {
        return fechaComienzo;
    }

    public void setFechaComienzo(String fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}
