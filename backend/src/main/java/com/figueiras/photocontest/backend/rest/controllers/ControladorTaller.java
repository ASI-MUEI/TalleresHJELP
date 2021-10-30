package com.figueiras.photocontest.backend.rest.controllers;

import com.figueiras.photocontest.backend.model.entities.Asistencia;
import com.figueiras.photocontest.backend.model.entities.Horarios;
import com.figueiras.photocontest.backend.model.exceptions.CampoVacioException;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.model.exceptions.ParseFormatException;
import com.figueiras.photocontest.backend.model.services.Block;
import com.figueiras.photocontest.backend.model.services.ServicioTaller;
import com.figueiras.photocontest.backend.rest.conversor.AsistenciaConversor;
import com.figueiras.photocontest.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Slice<Horarios> recuperarHorariosDisp(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size) {
        return servicioTaller.getHorariosDisponibles(page, size);
    }
}
