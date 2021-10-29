package com.figueiras.photocontest.backend.rest.conversor;

import com.figueiras.photocontest.backend.model.entities.EstadoAsistencias;
import com.figueiras.photocontest.backend.model.entities.PuestoTaller;
import com.figueiras.photocontest.backend.model.entities.TipoAsistencias;
import com.figueiras.photocontest.backend.model.services.Block;
import com.figueiras.photocontest.backend.rest.dtos.EstadoAsistenciasDto;
import com.figueiras.photocontest.backend.rest.dtos.FranjaHorariaDto;
import com.figueiras.photocontest.backend.rest.dtos.PuestoTallerDto;
import com.figueiras.photocontest.backend.rest.dtos.TipoAsistenciasDto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TallerConversor {

    /*public static List<PuestoTallerVehiculosDto> toPuestosTallerVehiculosTablaDto(List<PuestoTallerVehiculo> puestosTallerVehs) {

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
    }*/

    public static PuestoTallerDto toPuestoTDto(PuestoTaller puestoTaller){
        PuestoTallerDto puestoTallerDto = new PuestoTallerDto();
        puestoTallerDto.setIdPuestoTaller(puestoTaller.getIdPuestoTaller());
        puestoTallerDto.setNombre(puestoTaller.getNombre());
        puestoTallerDto.setDescripcion(puestoTaller.getDescripcion());
        return puestoTallerDto;
    }

    public static EstadoAsistenciasDto toEstAsistenciasDto(EstadoAsistencias estado){
        return new EstadoAsistenciasDto(estado.getIdEstado(), estado.getNombre(), estado.getDescripcion());
    }

    public static TipoAsistenciasDto toTiposAsistenciasDto(TipoAsistencias tipos){
        return new TipoAsistenciasDto(tipos.getIdTipo(), tipos.getNombre(), tipos.getDescripcion());
    }

//   public static List<FranjaHorariaDto> toFranjasHorariasDto(Block<PlanHorarios> planHorarios){
//       List<FranjaHorariaDto> franjasH = new ArrayList<>();
//       Iterator< PlanHorarios > it = planHorarios.getItems().iterator();
//       while (it.hasNext()) {
//           franjasH.add(new FranjaHorariaDto(it.next().getFranjaHoraria().getIdFranjaHoraria(), it.next().getFranjaHoraria().getFranjaHoraria()));
//       }
//       return franjasH;
//   }

}
