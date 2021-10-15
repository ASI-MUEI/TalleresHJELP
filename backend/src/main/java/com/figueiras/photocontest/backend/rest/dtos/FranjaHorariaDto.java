package com.figueiras.photocontest.backend.rest.dtos;

public class FranjaHorariaDto {
    private Long idFranjaHoraria;
    private String franjaHoraria;

    public FranjaHorariaDto(Long idFranjaHoraria, String franjaHoraria) {
        this.idFranjaHoraria = idFranjaHoraria;
        this.franjaHoraria = franjaHoraria;
    }

    public Long getIdFranjaHoraria() {
        return idFranjaHoraria;
    }

    public void setIdFranjaHoraria(Long idFranjaHoraria) {
        this.idFranjaHoraria = idFranjaHoraria;
    }

    public String getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(String franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }
}
