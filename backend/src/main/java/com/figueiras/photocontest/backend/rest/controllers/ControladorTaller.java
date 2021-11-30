package com.figueiras.photocontest.backend.rest.controllers;

import com.figueiras.photocontest.backend.model.entities.*;
import com.figueiras.photocontest.backend.model.exceptions.CampoVacioException;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.model.exceptions.ParseFormatException;
import com.figueiras.photocontest.backend.model.exceptions.StateErrorException;
import com.figueiras.photocontest.backend.model.services.Block;
import com.figueiras.photocontest.backend.model.services.ServicioTaller;
import com.figueiras.photocontest.backend.rest.conversor.AsistenciaConversor;
import com.figueiras.photocontest.backend.rest.conversor.TallerConversor;
import com.figueiras.photocontest.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/taller")
public class ControladorTaller {

    @Autowired
    private ServicioTaller servicioTaller;

    @PostMapping("/asistencia")
    public ResponseEntity registrarAsistencia(@RequestBody AsistenciasDto asistenciasDto)
            throws ParseFormatException, InstanceNotFoundException {
        servicioTaller.createAsistencia(asistenciasDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/trabajo")
    public ResponseEntity registrarTrabajo(@RequestBody TrabajoDto trabajoDto)
            throws CampoVacioException, InstanceNotFoundException {
        servicioTaller.createTrabajo(trabajoDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/asistencias/updateFranjaHoraria")
    public AsistenciaCompletaFranjaHDto actualizarHoraAsistencia(@RequestBody AsistenciaFranjaHorariaDto asistenciaFranjaHorariaDto) throws InstanceNotFoundException {
        return servicioTaller.asignarAsistenciaFranjaHoraria(asistenciaFranjaHorariaDto);
    }

    @PutMapping("/asistencias/updatePuesto")
    public Asistencia actualizarPuestoAsistencias(@RequestBody AsistenciaPuestoTDto asistenciaPuestoTDto)
            throws InstanceNotFoundException {
        return servicioTaller.asignarAsistenciaPuesto(asistenciaPuestoTDto);
    }

    @GetMapping("/asistencias")
    public Block<Asistencia> recuperarAsistencias(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size) {
        return servicioTaller.findAllAsistencias(page, size);
    }

    @GetMapping("/asistencias/{idAsistencia}/piezas")
    public Block<PiezasAsistenciasDto> recuperarPiezasByReparacion(@PathVariable Long idAsistencia, @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int size) throws InstanceNotFoundException {
        Slice<Pieza> piezas = servicioTaller.getPiezasByAsistencia(idAsistencia, page, size);
        List<PiezasAsistenciasDto> listaPiezas =  AsistenciaConversor.toPiezasReparacion(piezas.getContent());
        List<PiezasAsistenciasDto> listaPiezasTransformadas = servicioTaller.getNumeroUnidadesPiezaAsistencia(listaPiezas, idAsistencia);
        Block<PiezasAsistenciasDto> resultado = new Block<>(listaPiezasTransformadas, piezas.hasNext());

        return resultado;
    }

    @PutMapping("/asistencias/updatePieza")
    public Asistencia anadirPiezasReparacion(@RequestBody AsistenciaNuevaPiezaDto asistenciaNuevaPiezaDto)
            throws InstanceNotFoundException {
        return servicioTaller.asignarAsistenciaPieza(asistenciaNuevaPiezaDto);
    }

    @PutMapping("/asistencias/removePieza")
    public Asistencia eliminarPiezasReparacion(@RequestBody AsistenciaNuevaPiezaDto asistenciaNuevaPiezaDto)
            throws InstanceNotFoundException {
        return servicioTaller.deleteAsistenciaPieza(asistenciaNuevaPiezaDto);
    }

    @GetMapping("/asistencias/{fecha}")
    public List<AsistenciasDto> recuperarAsistenciasPorFecha(@PathVariable String fecha){
        List<Asistencia> asistencias = servicioTaller.findAllAsistenciasPorFecha(fecha);
        // Si la asistencia llega nula, se pone nulo en la salida. Es la manera de indicar que ese slot no está asignado
        // al frontend.
        List<AsistenciasDto> resultado = new ArrayList<>();

        for(int i = 0; i <= 100; i ++){
            if(asistencias.get(i) == null){
                resultado.add(null);
            } else {
                AsistenciasDto aDto = AsistenciaConversor.toAsistenciasDto(asistencias.get(i));
                resultado.add(aDto);
            }
        }

        return resultado;
    }

    @GetMapping("/asistencias/horarios")
    public List<HorariosAsistenciasDto> recuperarHorariosDisp() {
        return AsistenciaConversor.toHorariosAsistenciaDto(servicioTaller.getHorariosDisponibles());
    }

    @GetMapping("/trabajo/activos")
    public List<MatriculasActivasDto> getTrabajosActivos() {
        return TallerConversor.toMatriculasActivasDto(servicioTaller.getTrabajosAbiertos());
    }

    @GetMapping("/trabajo")
    public Block<ListadoTrabajosDto> listarTrabajos(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "5") int size) {
        Slice<Trabajo> trabajos = servicioTaller.getTrabajosOrderByFecha(page, size);
        List<ListadoTrabajosDto> trabajosDto = TallerConversor.toListadoTrabajosDto(trabajos.getContent());
        Block<ListadoTrabajosDto> resultado = new Block<>(trabajosDto, trabajos.hasNext());
        return resultado;
    }

    @GetMapping("/trabajo/{idTrabajo}/reparaciones")
    public Block<ListarReparacionesDto> listarReparaciones(@PathVariable Long idTrabajo,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size) {
        Slice<Asistencia> asistencias = servicioTaller.getAsistenciasOrderByFecha(idTrabajo, page, size);
        List<ListarReparacionesDto> reparacionesDto = AsistenciaConversor.toListarAsistenciasDto(asistencias.getContent());
        Block<ListarReparacionesDto> resultado = new Block<>(reparacionesDto, asistencias.hasNext());

        return resultado;
    }

    @GetMapping("/trabajo/{idTrabajo}")
    public TrabajoCompletoDto getTrabajoByID(@PathVariable Long idTrabajo) throws InstanceNotFoundException {
        return TallerConversor.toTrabajoCompletoDto(servicioTaller.getTrabajoByID(idTrabajo));
    }

    @GetMapping("/reparacion/{idReparacion}")
    public AsistenciaCompletaDto getReparacionByID(@PathVariable Long idReparacion) throws InstanceNotFoundException {
        return AsistenciaConversor.toAsistenciaCompletaDto(servicioTaller.getAsistenciaByID(idReparacion));
    }


    @GetMapping("/elevadores")
    public List<PuestoTallerDto> getElevadores() {
        return TallerConversor.toPuestosDto(servicioTaller.getElevadores());
    }

    @GetMapping("/horariosLibres/{fecha}")
    public ArrayList<List<Horarios>> getHorariosLibresPorFecha(@PathVariable String fecha) {
        return servicioTaller.getHorariosLibresporFecha(fecha);
    }

    @PostMapping("/tipoTarea")
    public ResponseEntity registrarTipoTarea(@RequestBody TipoTareaDto tipoTareaDto) {
        servicioTaller.crearTipoAsistencia(tipoTareaDto.getNombre(), tipoTareaDto.getDescripcion());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/tiposTarea")
    public Slice<TipoAsistencias> getTiposTarea() {
        return servicioTaller.getTipoAssitencias();
    }

    @GetMapping("/factura/{idTrabajo}")
    public String getFactura(@PathVariable Long idTrabajo) throws StateErrorException, InstanceNotFoundException {
        return servicioTaller.getFactura(idTrabajo);
    }

    @GetMapping("/piezas")
    public List<PiezasAsistenciasDto> getPiezas(){
        return servicioTaller.getAllPiezas();
    }

    @PutMapping("/asistencia/{idAsistencia}/update/retraso")
    public ResponseEntity cambiarRetraso(@PathVariable Long idAsistencia, @RequestBody String motivo){
        servicioTaller.cambiarRetraso(idAsistencia, motivo);

        return new ResponseEntity(HttpStatus.CREATED);
    }

}
