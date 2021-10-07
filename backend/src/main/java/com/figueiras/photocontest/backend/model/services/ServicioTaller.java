package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.model.exceptions.ParseFormatException;
import com.figueiras.photocontest.backend.rest.dtos.PuestoTallerVehiculoDto;

public interface ServicioTaller {
    void asignarVehiculoPuesto(PuestoTallerVehiculoDto puestoTallerVehDto)  throws InstanceNotFoundException, ParseFormatException;
}
