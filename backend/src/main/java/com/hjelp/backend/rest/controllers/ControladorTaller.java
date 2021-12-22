package com.hjelp.backend.rest.controllers;

import com.hjelp.backend.model.entities.*;
import com.hjelp.backend.model.exceptions.*;
import com.hjelp.backend.model.services.Block;
import com.hjelp.backend.model.services.ServicioTaller;
import com.hjelp.backend.rest.conversor.AsistenciaConversor;
import com.hjelp.backend.rest.conversor.TallerConversor;
import com.hjelp.backend.rest.dtos.*;
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


    /***
     * US05
     */
    @GetMapping("/asistencias")
    public Block<Asistencia> recuperarAsistencias(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int size) {
        return servicioTaller.findAllAsistencias(page, size);
    }

    /***
     * US05
     */
    @GetMapping("/tiposTarea")
    public List<TipoAsistencias> getTiposTarea() {
        return servicioTaller.getTipoAsitencias();
    }

    /***
     * US05
     */
    @GetMapping("/trabajo/activos")
    public List<MatriculasActivasDto> getTrabajosActivos() {
        return TallerConversor.toMatriculasActivasDto(servicioTaller.getTrabajosAbiertos());
    }

    /***
     * US05, US29, US35
     */
    @GetMapping("/trabajo/{idTrabajo}/reparaciones")
    public Block<ListarReparacionesDto> listarReparaciones(@PathVariable Long idTrabajo,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "5") int size) {
        Slice<Asistencia> asistencias = servicioTaller.getAsistenciasOrderByFecha(idTrabajo, page, size);
        List<ListarReparacionesDto> reparacionesDto = AsistenciaConversor.toListarAsistenciasDto(asistencias.getContent());

        return new Block<>(reparacionesDto, asistencias.hasNext());
    }

    /***
     * US05, US40
     */
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

    /***
     * US06
     */
    @PostMapping("/asistencia")
    public ResponseEntity registrarAsistencia(@RequestBody AsistenciasDto asistenciasDto)
            throws ParseFormatException, InstanceNotFoundException {
        servicioTaller.createAsistencia(asistenciasDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /***
     * US06
     */
    @GetMapping("/elevadores")
    public List<PuestoTallerDto> getElevadores() {
        return TallerConversor.toPuestosDto(servicioTaller.getElevadores());
    }

    /***
     * US06, US07
     */
    @GetMapping("/asistencias/horarios")
    public List<HorariosAsistenciasDto> recuperarHorariosDisp() {
        return AsistenciaConversor.toHorariosAsistenciaDto(servicioTaller.getHorariosDisponibles());
    }

    /***
     * US07
     */
    @PutMapping("/asistencias/updateFranjaHoraria")
    public AsistenciaCompletaFranjaHDto actualizarHoraAsistencia(@RequestBody AsistenciaFranjaHorariaDto asistenciaFranjaHorariaDto) throws InstanceNotFoundException {
        return servicioTaller.asignarAsistenciaFranjaHoraria(asistenciaFranjaHorariaDto);
    }

    /***
     * US07
     */
    @PutMapping("/asistencia/{idAsistencia}")
    public ResponseEntity actualizarAsistencia(@RequestBody AsistenciasDto asistenciasDto,
                                               @PathVariable long idAsistencia) throws InstanceNotFoundException {
        servicioTaller.actualizarAsistencia(asistenciasDto, idAsistencia);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    /***
     * US07
     */
    @PutMapping("/asistencias/updatePuesto")
    public Asistencia actualizarPuestoAsistencias(@RequestBody AsistenciaPuestoTDto asistenciaPuestoTDto)
            throws InstanceNotFoundException {
        return servicioTaller.asignarAsistenciaPuesto(asistenciaPuestoTDto);
    }

    /***
     * US07
     */
    @PutMapping("/asistencias/update/horas")
    public ResponseEntity actualizarHorariosAsistencia(@RequestBody AsistenciaFranjaHorariaDto asistenciaFranjaHorariaDto) throws InstanceNotFoundException {
        servicioTaller.actualizaFechaYHoraAsistencia(asistenciaFranjaHorariaDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /***
     * US09
     */
    @GetMapping("/reparacion/{idReparacion}")
    public AsistenciaCompletaDto getReparacionByID(@PathVariable Long idReparacion) throws InstanceNotFoundException {
        return AsistenciaConversor.toAsistenciaCompletaDto(servicioTaller.getAsistenciaByID(idReparacion));
    }

    /***
     * US18, US39
     */
    @PostMapping("/trabajo")
    public ResponseEntity registrarTrabajo(@RequestBody TrabajoDto trabajoDto)
            throws InstanceNotFoundException, CamposIntroducidosNoValidosException {
        servicioTaller.createTrabajo(trabajoDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /***
     * US23, US30
     */
    @GetMapping("/trabajo/{idTrabajo}")
    public TrabajoCompletoDto getTrabajoByID(@PathVariable Long idTrabajo) throws InstanceNotFoundException {
        return TallerConversor.toTrabajoCompletoDto(servicioTaller.getTrabajoByID(idTrabajo));
    }

    /***
     * US24, US31
     */
    @PutMapping("/trabajo/{idTrabajo}/estado")
    public ResponseEntity cambiarEstadoTrabajo(@PathVariable Long idTrabajo, @RequestBody String estado) throws InstanceNotFoundException {
        servicioTaller.cambiarEstadoTrabajo(idTrabajo, estado);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /***
     * US25
     */
    @GetMapping("/trabajo")
    public Block<ListadoTrabajosDto> listarTrabajos(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size) {
        Slice<Trabajo> trabajos = servicioTaller.getTrabajosOrderByFecha(page, size);
        List<ListadoTrabajosDto> trabajosDto = TallerConversor.toListadoTrabajosDto(trabajos.getContent());
        Block<ListadoTrabajosDto> resultado = new Block<>(trabajosDto, trabajos.hasNext());
        return resultado;
    }

    /**
     * US29
     */
    @GetMapping("/piezas")
    public List<PiezasAsistenciasDto> getPiezas(){
        return servicioTaller.getAllPiezas();
    }

    /***
     * US32
     */
    @PutMapping("/asistencias/updatePieza")
    public Asistencia anadirPiezasReparacion(@RequestBody AsistenciaNuevaPiezaDto asistenciaNuevaPiezaDto)
            throws InstanceNotFoundException {
        return servicioTaller.asignarAsistenciaPieza(asistenciaNuevaPiezaDto);
    }

    /***
     * US33
     */
    @GetMapping("/asistencias/{idAsistencia}/piezas")
    public Block<PiezasAsistenciasDto> recuperarPiezasByReparacion(@PathVariable Long idAsistencia, @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "5") int size) throws InstanceNotFoundException {
        Slice<Pieza> piezas = servicioTaller.getPiezasByAsistencia(idAsistencia, page, size);
        List<PiezasAsistenciasDto> listaPiezas =  AsistenciaConversor.toPiezasReparacion(piezas.getContent());
        List<PiezasAsistenciasDto> listaPiezasTransformadas = servicioTaller.getNumeroUnidadesPiezaAsistencia(listaPiezas, idAsistencia);
        Block<PiezasAsistenciasDto> resultado = new Block<>(listaPiezasTransformadas, piezas.hasNext());

        return resultado;
    }

    /***
     * US34
     */
    @PutMapping("/asistencia/{idAsistencia}/update/retraso")
    public ResponseEntity cambiarRetraso(@PathVariable Long idAsistencia, @RequestBody String motivo) throws InstanceNotFoundException, CampoVacioException {
        servicioTaller.cambiarRetraso(idAsistencia, motivo);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /***
     * US37
     */
    @GetMapping("/asistencias/retrasadas")
    public Block<AsistenciaCompletaDto> getAsistenciasRetrasadas(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "5") int size){
        Slice<Asistencia> asistencias = servicioTaller.getAsistenciasRetrasadas(page, size);
        List<AsistenciaCompletaDto> asistenciasList = AsistenciaConversor.toListAsistenciaCompletaDto(asistencias);
        return new Block<>(asistenciasList, asistencias.hasNext());
    }

    /***
     * US38
     */
    @GetMapping("/factura/{idTrabajo}")
    public FacturaDto getFactura(@PathVariable Long idTrabajo) throws StateErrorException, InstanceNotFoundException {
        FacturaDto resultado = new FacturaDto();
        resultado.setCuerpoFactura(servicioTaller.getFactura(idTrabajo));
        return resultado;
    }

/************************************************************************************************************************/
    @PutMapping("/asistencias/removePieza")
    public Asistencia eliminarPiezasReparacion(@RequestBody AsistenciaNuevaPiezaDto asistenciaNuevaPiezaDto)
            throws InstanceNotFoundException {
        return servicioTaller.deleteAsistenciaPieza(asistenciaNuevaPiezaDto);
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
}
