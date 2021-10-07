package com.figueiras.photocontest.backend.rest.conversor;

import com.figueiras.photocontest.backend.model.entities.PuestoTaller;
import com.figueiras.photocontest.backend.model.entities.PuestoTallerVehiculo;
import com.figueiras.photocontest.backend.rest.dtos.PuestoTallerDto;
import com.figueiras.photocontest.backend.rest.dtos.PuestoTallerVehiculosDto;

import java.util.ArrayList;
import java.util.List;

public class TallerConversor {

    public static List<PuestoTallerVehiculosDto> toPuestosTallerVehiculosTablaDto(List<PuestoTallerVehiculo> puestosTallerVehs) {

        List<PuestoTallerVehiculosDto> puestosTallerVehiculosTbla = new ArrayList<>();

        for (PuestoTallerVehiculo ptv : puestosTallerVehs) {

            puestosTallerVehiculosTbla.add(toPuestoTVehTablaDto(ptv));
        }
        return puestosTallerVehiculosTbla;
    }

    public static PuestoTallerVehiculosDto toPuestoTVehTablaDto(PuestoTallerVehiculo puestoTallerVehiculo) {

        PuestoTallerVehiculosDto puestoTallerVehiculosDto = new PuestoTallerVehiculosDto();
        puestoTallerVehiculosDto.setPuesto(toPuestoTDto(puestoTallerVehiculo.getPuesto()));
        puestoTallerVehiculosDto.setVehiculo(VehiculoConversor.toVehiculoDto(puestoTallerVehiculo.getVehiculo()));
        puestoTallerVehiculosDto.setFechaComienzo(puestoTallerVehiculo.getFechaComienzo());
        puestoTallerVehiculosDto.setFechaFinal(puestoTallerVehiculo.getFechaFinal());
        return puestoTallerVehiculosDto;
    }

    public static PuestoTallerDto toPuestoTDto(PuestoTaller puestoTaller){
        PuestoTallerDto puestoTallerDto = new PuestoTallerDto();
        puestoTallerDto.setIdPuestoTaller(puestoTaller.getIdPuestoTaller());
        puestoTallerDto.setNombre(puestoTaller.getNombre());
        puestoTallerDto.setDescripcion(puestoTaller.getDescripcion());
        return puestoTallerDto;
    }

}
