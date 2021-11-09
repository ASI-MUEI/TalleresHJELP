package com.figueiras.photocontest.backend.rest.dtos;

public class MecanicoAsistenciaDto {
    private Long idMecanico;
    private String nombreMecanico;

    public MecanicoAsistenciaDto() {
    }

    public MecanicoAsistenciaDto(Long idMecanico, String nombreMecanico) {
        this.idMecanico = idMecanico;
        this.nombreMecanico = nombreMecanico;
    }

    public Long getIdMecanico() {
        return idMecanico;
    }

    public void setIdMecanico(Long idMecanico) {
        this.idMecanico = idMecanico;
    }

    public String getNombreMecanico() {
        return nombreMecanico;
    }

    public void setNombreMecanico(String nombreMecanico) {
        this.nombreMecanico = nombreMecanico;
    }
}
