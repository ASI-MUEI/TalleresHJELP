package com.hjelp.backend.rest.conversor;

import com.hjelp.backend.model.entities.EstadoTrabajo;
import com.hjelp.backend.model.entities.PuestoTaller;
import com.hjelp.backend.model.entities.TipoAsistencias;
import com.hjelp.backend.model.entities.Trabajo;
import com.hjelp.backend.rest.dtos.*;
import org.springframework.data.domain.Slice;

import java.time.ZoneId;
import java.util.ArrayList;
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

    public static List<PuestoTallerDto> toPuestosDto(Slice<PuestoTaller> elevadores){
        List<PuestoTallerDto> puestosDto = new ArrayList<>();
        for (PuestoTaller elevador : elevadores){
            puestosDto.add(toPuestoTDto(elevador));
        }
        return puestosDto;
    }

    public static PuestoTallerDto toPuestoTDto(PuestoTaller puestoTaller){
        PuestoTallerDto puestoTallerDto = new PuestoTallerDto();
        puestoTallerDto.setIdPuestoTaller(puestoTaller.getIdPuesto());
        puestoTallerDto.setNombre(puestoTaller.getNombre());
        puestoTallerDto.setDescripcion(puestoTaller.getDescripcion());
        return puestoTallerDto;
    }

    public static EstadoAsistenciasDto toEstAsistenciasDto(EstadoTrabajo estado){
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

    public static List<MatriculasActivasDto> toMatriculasActivasDto(Slice<Trabajo> trabajos){
        List<MatriculasActivasDto> result = new ArrayList<>();
        for (Trabajo trabajo : trabajos){
            MatriculasActivasDto matriculaActiva = new MatriculasActivasDto(trabajo.getIdTrabajo(), trabajo.getVehiculo().getMatricula(), trabajo.getPeritado());
            if (matriculaActiva.getPeritado())
                matriculaActiva.setMatriculaPeritada(matriculaActiva.getMatricula() + " (Peritada)");
            else
                matriculaActiva.setMatriculaPeritada(matriculaActiva.getMatricula());
            result.add(matriculaActiva);
        }
        return result;
    }

    public static List<ListadoTrabajosDto> toListadoTrabajosDto(List<Trabajo> trabajos){
        List<ListadoTrabajosDto> result = new ArrayList<>();
        for (Trabajo trabajo : trabajos){
            ListadoTrabajosDto trabajoDto = toListadoTrabajoDto(trabajo);
            result.add(trabajoDto);
        }
        return result;
    }

    public static ListadoTrabajosDto toListadoTrabajoDto(Trabajo trabajo){
        ListadoTrabajosDto result = new ListadoTrabajosDto();

        result.setIdTrabajo(trabajo.getIdTrabajo());
        result.setFecha(trabajo.getFechaCreado().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        result.setIdCliente(trabajo.getVehiculo().getUsuario().getIdUsuario());
        result.setNombreCliente(trabajo.getVehiculo().getUsuario().getNombreUsuario());
        result.setEstado(trabajo.getEstado().getNombre());
        result.setMatricula(trabajo.getVehiculo().getMatricula());

        return result;
    }

    public static TrabajoCompletoDto toTrabajoCompletoDto(Trabajo trabajo){
        return new TrabajoCompletoDto(trabajo.getIdTrabajo(), trabajo.getNombre(),trabajo.getDescripcion(),
                trabajo.getVehiculo().getMatricula(), trabajo.getVehiculo().getUsuario().getNombreUsuario(),
                trabajo.getEstado().getNombre(), trabajo.getFechaCreado().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                trabajo.getPeritado());
    }

}
