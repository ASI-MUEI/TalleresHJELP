package com.figueiras.photocontest.backend.rest.controllers;

import com.figueiras.photocontest.backend.model.exceptions.CampoDuplicadoException;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.model.services.ServicioVehiculo;
import com.figueiras.photocontest.backend.rest.dtos.VehiculoDto;
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


    @PostMapping("/registrarVeh")
    public ResponseEntity registrarVehiculo(@RequestBody VehiculoDto vehiculoDto)
            throws CampoDuplicadoException,  InstanceNotFoundException {

        servicioVehiculo.registrarVehiculo(vehiculoDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/matriculas")
    public List<String> recuperarMatriculas(){
        return servicioVehiculo.getTodasMatriculas();
    }
}
