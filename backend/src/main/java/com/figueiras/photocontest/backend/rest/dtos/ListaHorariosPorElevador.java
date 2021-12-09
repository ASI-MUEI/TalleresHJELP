package com.figueiras.photocontest.backend.rest.dtos;

import com.figueiras.photocontest.backend.model.entities.Horarios;

import java.util.List;

public class ListaHorariosPorElevador {

    private List<Horarios> horariosElevador1;
    private List<Horarios> horariosElevador2;
    private List<Horarios> horariosElevador3;
    private List<Horarios> horariosElevador4;
    private List<Horarios> horariosElevador5;

    public ListaHorariosPorElevador() {
    }

    public List<Horarios> getHorariosElevador1() {
        return horariosElevador1;
    }

    public void setHorariosElevador1(List<Horarios> horariosElevador1) {
        this.horariosElevador1 = horariosElevador1;
    }

    public List<Horarios> getHorariosElevador2() {
        return horariosElevador2;
    }

    public void setHorariosElevador2(List<Horarios> horariosElevador2) {
        this.horariosElevador2 = horariosElevador2;
    }

    public List<Horarios> getHorariosElevador3() {
        return horariosElevador3;
    }

    public void setHorariosElevador3(List<Horarios> horariosElevador3) {
        this.horariosElevador3 = horariosElevador3;
    }

    public List<Horarios> getHorariosElevador4() {
        return horariosElevador4;
    }

    public void setHorariosElevador4(List<Horarios> horariosElevador4) {
        this.horariosElevador4 = horariosElevador4;
    }

    public List<Horarios> getHorariosElevador5() {
        return horariosElevador5;
    }

    public void setHorariosElevador5(List<Horarios> horariosElevador5) {
        this.horariosElevador5 = horariosElevador5;
    }
}
