package com.figueiras.photocontest.backend.rest.controllers;

import com.figueiras.photocontest.backend.model.entities.PuestoTallerVehiculo;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.model.exceptions.ParseFormatException;
import com.figueiras.photocontest.backend.model.services.Block;
import com.figueiras.photocontest.backend.model.services.ServicioTaller;
import com.figueiras.photocontest.backend.rest.conversor.TallerConversor;
import com.figueiras.photocontest.backend.rest.dtos.PuestoTallerVehiculoDto;
import com.figueiras.photocontest.backend.rest.dtos.PuestoTallerVehiculosDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class ControladorTaller {

    @Autowired
    private ServicioTaller servicioTaller;

    @PostMapping("/taller/asignarPuesto")
    public ResponseEntity registrarVehPuestoTaller(@RequestBody PuestoTallerVehiculoDto puestoTallerVehiculoDto)
            throws ParseFormatException, InstanceNotFoundException {

        servicioTaller.asignarVehiculoPuesto(puestoTallerVehiculoDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/taller/puestoTallerVehiculos")
    public Block<PuestoTallerVehiculosDto> recuperarPuestosYVehiculos(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size) {
        Block<PuestoTallerVehiculo> blockPuestoTVeh = servicioTaller.findAllPuestosTVehiculos(page, size);
        Block<PuestoTallerVehiculosDto> puestoTVBlock =
                new Block<>(TallerConversor.toPuestosTallerVehiculosTablaDto(blockPuestoTVeh.getItems()),
                        blockPuestoTVeh.getExistMoreItems());

        return puestoTVBlock;
    }
}
