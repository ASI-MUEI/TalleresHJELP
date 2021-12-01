package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.*;
import com.figueiras.photocontest.backend.model.exceptions.CampoVacioException;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.model.exceptions.ParseFormatException;
import com.figueiras.photocontest.backend.model.exceptions.StateErrorException;
import com.figueiras.photocontest.backend.rest.dtos.*;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.List;

public interface ServicioTaller {
    Asistencia asignarAsistenciaPuesto(AsistenciaPuestoTDto asistenciaPuestoTDto)  throws InstanceNotFoundException;
    Block<Asistencia> findAllAsistencias(int page, int size);
    List<Asistencia> findAllAsistenciasPorFecha(String fecha);
    AsistenciaCompletaFranjaHDto asignarAsistenciaFranjaHoraria(AsistenciaFranjaHorariaDto asistenciaFranjaHDto) throws InstanceNotFoundException;
    Asistencia createAsistencia(AsistenciasDto asistenciasDto) throws InstanceNotFoundException, ParseFormatException;
    List<Horarios> getHorariosDisponibles();
    Trabajo createTrabajo(TrabajoDto trabajoDto) throws InstanceNotFoundException, CampoVacioException;
    Slice<Trabajo> getTrabajosAbiertos();
    Slice<PuestoTaller> getElevadores();
    Slice<Trabajo> getTrabajosOrderByFecha(int page, int size);
    Slice<Asistencia> getAsistenciasOrderByFecha(Long idTrabajo, int page, int size);
    Trabajo getTrabajoByID(Long idTrabajo)throws InstanceNotFoundException;
    Asistencia getAsistenciaByID(Long idAsistencia)throws InstanceNotFoundException;
    ArrayList<List<Horarios>> getHorariosLibresporFecha(String fecha);
    TipoAsistencias crearTipoAsistencia(String nombre, String descripcion);
    Slice<TipoAsistencias> getTipoAssitencias();
    Slice<Pieza> getPiezasByAsistencia(Long idAsistencia, int page, int size) throws InstanceNotFoundException;
    Asistencia asignarAsistenciaPieza(AsistenciaNuevaPiezaDto asistenciaNuevaPiezaDto) throws InstanceNotFoundException;
    Asistencia deleteAsistenciaPieza(AsistenciaNuevaPiezaDto asistenciaNuevaPiezaDto) throws InstanceNotFoundException;
    String getFactura(Long idTrabajo) throws InstanceNotFoundException, StateErrorException;
    List<PiezasAsistenciasDto> getNumeroUnidadesPiezaAsistencia(List<PiezasAsistenciasDto> asistenciaPiezasDto, Long idAsistencia);
    List<PiezasAsistenciasDto> getAllPiezas();
    void cambiarRetraso(Long idAsistencia, String motivo) throws InstanceNotFoundException, CampoVacioException;
    Slice<Asistencia> getAsistenciasRetrasadas(int page, int size);
    void cambiarEstadoTrabajo(Long idTrabajo, Long idEstado) throws InstanceNotFoundException;
    void actualizaFechaYHoraAsistencia(AsistenciaFranjaHorariaDto asistenciaFranjaHorariaDto) throws InstanceNotFoundException;
}
