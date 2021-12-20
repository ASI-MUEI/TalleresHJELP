package com.hjelp.backend.rest.dtos;

public class MatriculasActivasDto {
    private Long idTrabajo;
    private String matricula;
    private Boolean peritado;
    private String matriculaPeritada;

    public MatriculasActivasDto(Long idTrabajo, String matricula, Boolean peritado) {
        this.idTrabajo = idTrabajo;
        this.matricula = matricula;
        this.peritado = peritado;
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

    public Boolean getPeritado() {
        return peritado;
    }

    public void setPeritado(Boolean peritado) {
        this.peritado = peritado;
    }

    public String getMatriculaPeritada() {
        return matriculaPeritada;
    }

    public void setMatriculaPeritada(String matriculaPeritada) {
        this.matriculaPeritada = matriculaPeritada;
    }
}
