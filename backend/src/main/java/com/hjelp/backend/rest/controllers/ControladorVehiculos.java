package com.hjelp.backend.rest.controllers;

import com.hjelp.backend.model.exceptions.CampoDuplicadoException;
import com.hjelp.backend.model.exceptions.InstanceNotFoundException;
import com.hjelp.backend.model.services.ServicioVehiculo;
import com.hjelp.backend.rest.dtos.VehiculoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogo-vehiculos")
public class ControladorVehiculos {

    @Autowired
    private ServicioVehiculo servicioVehiculo;

    /***
     * US27
     */
    @GetMapping("/matriculas")
    public List<String> recuperarMatriculas(){
        return servicioVehiculo.getTodasMatriculas();
    }

    /***
     * US28
     */
    @PostMapping("/registrarVeh")
    public ResponseEntity registrarVehiculo(@RequestBody VehiculoDto vehiculoDto)
            throws CampoDuplicadoException,  InstanceNotFoundException {

        servicioVehiculo.registrarVehiculo(vehiculoDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
