package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.Asistencia;
import com.figueiras.photocontest.backend.model.entities.Horarios;
import com.figueiras.photocontest.backend.model.entities.PlanHorarios;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.model.exceptions.ParseFormatException;
import com.figueiras.photocontest.backend.rest.dtos.AsistenciaCompletaFranjaHDto;
import com.figueiras.photocontest.backend.rest.dtos.AsistenciaFranjaHorariaDto;
import com.figueiras.photocontest.backend.rest.dtos.AsistenciaPuestoTDto;
import com.figueiras.photocontest.backend.rest.dtos.AsistenciasDto;
import org.springframework.data.domain.Slice;

public interface ServicioTaller {
    Asistencia asignarAsistenciaPuesto(AsistenciaPuestoTDto asistenciaPuestoTDto)  throws InstanceNotFoundException;
    Block<Asistencia> findAllAsistencias(int page, int size);
    Block<PlanHorarios> findHoraByAsistencia(Long idAsistencia);
    AsistenciaCompletaFranjaHDto asignarAsistenciaFranjaHoraria(AsistenciaFranjaHorariaDto asistenciaFranjaHDto) throws InstanceNotFoundException;
    Asistencia createAsistencia(AsistenciasDto asistenciasDto) throws InstanceNotFoundException, ParseFormatException;
    Slice<Horarios> getHorariosDisponibles(int page, int size);
}
