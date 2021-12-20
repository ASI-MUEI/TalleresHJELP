package com.hjelp.backend.model.services;

import com.hjelp.backend.model.entities.*;
import com.hjelp.backend.model.exceptions.*;
import com.hjelp.backend.rest.dtos.*;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.List;

public interface ServicioTaller {
    Asistencia asignarAsistenciaPuesto(AsistenciaPuestoTDto asistenciaPuestoTDto)  throws InstanceNotFoundException; /*** US07 */
    Block<Asistencia> findAllAsistencias(int page, int size); /*** US05 */
    List<Asistencia> findAllAsistenciasPorFecha(String fecha); /*** US05, US40 */
    AsistenciaCompletaFranjaHDto asignarAsistenciaFranjaHoraria(AsistenciaFranjaHorariaDto asistenciaFranjaHDto) throws InstanceNotFoundException; /*** US07  */
    Asistencia createAsistencia(AsistenciasDto asistenciasDto) throws InstanceNotFoundException, ParseFormatException; /*** US06  */
    List<Horarios> getHorariosDisponibles(); /*** US06, US07  */
    Trabajo createTrabajo(TrabajoDto trabajoDto) throws InstanceNotFoundException, CampoVacioException, CamposIntroducidosNoValidosException; /*** US18, US39 */
    Slice<Trabajo> getTrabajosAbiertos(); /*** US05  */
    Slice<PuestoTaller> getElevadores(); /*** US06  */
    Slice<Trabajo> getTrabajosOrderByFecha(int page, int size); /*** US25 */
    Slice<Asistencia> getAsistenciasOrderByFecha(Long idTrabajo, int page, int size); /*** US05, US29, US35  */
    Asistencia getAsistenciaByID(Long idAsistencia)throws InstanceNotFoundException; /*** US09 */
    ArrayList<List<Horarios>> getHorariosLibresporFecha(String fecha);
    TipoAsistencias crearTipoAsistencia(String nombre, String descripcion);
    List<TipoAsistencias> getTipoAsitencias(); /*** US05  */
    Slice<Pieza> getPiezasByAsistencia(Long idAsistencia, int page, int size) throws InstanceNotFoundException; /*** US33 */
    Asistencia asignarAsistenciaPieza(AsistenciaNuevaPiezaDto asistenciaNuevaPiezaDto) throws InstanceNotFoundException; /*** US32 */
    Asistencia deleteAsistenciaPieza(AsistenciaNuevaPiezaDto asistenciaNuevaPiezaDto) throws InstanceNotFoundException;
    String getFactura(Long idTrabajo) throws InstanceNotFoundException, StateErrorException;  /*** US38 */
    List<PiezasAsistenciasDto> getNumeroUnidadesPiezaAsistencia(List<PiezasAsistenciasDto> asistenciaPiezasDto, Long idAsistencia);
    List<PiezasAsistenciasDto> getAllPiezas(); /*** US29 */
    void cambiarRetraso(Long idAsistencia, String motivo) throws InstanceNotFoundException, CampoVacioException; /*** US34 */
    Slice<Asistencia> getAsistenciasRetrasadas(int page, int size); /*** US37 */
    void cambiarEstadoTrabajo(Long idTrabajo, String idEstado) throws InstanceNotFoundException; /*** US24, US31  */
    void actualizaFechaYHoraAsistencia(AsistenciaFranjaHorariaDto asistenciaFranjaHorariaDto) throws InstanceNotFoundException; /*** US07 */
    Asistencia actualizarAsistencia(AsistenciasDto asistenciasDto, long idAsistencia) throws InstanceNotFoundException;
    Trabajo getTrabajoByID(Long idTrabajo) throws InstanceNotFoundException; /*** US23, US30 */
}
