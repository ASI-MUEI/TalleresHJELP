package com.hjelp.backend.rest.dtos;


public class PuestoTallerVehiculosDto {

    private VehiculoCompletoDto vehiculo;
    private PuestoTallerDto puesto;
    private String fechaComienzo;
    private String fechaFinal;

    public VehiculoCompletoDto getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoCompletoDto vehiculo) {
        this.vehiculo = vehiculo;
    }

    public PuestoTallerDto getPuesto() {
        return puesto;
    }

    public void setPuesto(PuestoTallerDto puesto) {
        this.puesto = puesto;
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
