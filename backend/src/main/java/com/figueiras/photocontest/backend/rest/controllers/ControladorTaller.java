package com.figueiras.photocontest.backend.rest.controllers;

import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.model.exceptions.ParseFormatException;
import com.figueiras.photocontest.backend.model.services.ServicioTaller;
import com.figueiras.photocontest.backend.rest.dtos.PuestoTallerVehiculoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ControladorTaller {

    @Autowired
    private ServicioTaller servicioTaller;

    @PostMapping("/taller/asignarPuesto")
    public ResponseEntity registrarVehPuestoTaller(@RequestBody PuestoTallerVehiculoDto puestoTallerVehiculoDto)
            throws ParseFormatException, InstanceNotFoundException {

        servicioTaller.asignarVehiculoPuesto(puestoTallerVehiculoDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
