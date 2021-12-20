package com.hjelp.backend.rest.dtos;

public class MatrículasDispPorPerDto {
    private String matricula;
    private Boolean peritada;

    public MatrículasDispPorPerDto(String matricula, Boolean peritada) {
        this.matricula = matricula;
        this.peritada = peritada;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Boolean getPeritada() {
        return peritada;
    }

    public void setPeritada(Boolean peritada) {
        this.peritada = peritada;
    }
}
