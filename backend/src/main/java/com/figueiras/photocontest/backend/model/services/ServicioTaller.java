package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.Asistencia;
import com.figueiras.photocontest.backend.model.entities.Horarios;
import com.figueiras.photocontest.backend.model.entities.Trabajo;
import com.figueiras.photocontest.backend.model.exceptions.CampoVacioException;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.model.exceptions.ParseFormatException;
import com.figueiras.photocontest.backend.rest.dtos.*;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ServicioTaller {
    Asistencia asignarAsistenciaPuesto(AsistenciaPuestoTDto asistenciaPuestoTDto)  throws InstanceNotFoundException;
    Block<Asistencia> findAllAsistencias(int page, int size);
    List<Asistencia> findAllAsistenciasPorFecha(String fecha);
    AsistenciaCompletaFranjaHDto asignarAsistenciaFranjaHoraria(AsistenciaFranjaHorariaDto asistenciaFranjaHDto) throws InstanceNotFoundException;
    Asistencia createAsistencia(AsistenciasDto asistenciasDto) throws InstanceNotFoundException, ParseFormatException;
    Slice<Horarios> getHorariosDisponibles(int page, int size);
    Trabajo createTrabajo(TrabajoDto trabajoDto) throws InstanceNotFoundException, CampoVacioException;
}
