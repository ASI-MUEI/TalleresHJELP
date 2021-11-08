package com.figueiras.photocontest.backend.rest.dtos;

public class MatriculasActivasDto {
    private Long idTrabajo;
    private String matricula;

    public MatriculasActivasDto(Long idTrabajo, String matricula) {
        this.idTrabajo = idTrabajo;
        this.matricula = matricula;
    }

    public Long getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(Long idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
