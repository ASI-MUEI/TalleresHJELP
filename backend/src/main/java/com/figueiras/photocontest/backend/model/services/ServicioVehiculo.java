package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.Marca;
import com.figueiras.photocontest.backend.model.entities.Modelo;
import com.figueiras.photocontest.backend.model.entities.Vehiculo;
import com.figueiras.photocontest.backend.model.exceptions.CampoDuplicadoException;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.rest.dtos.MatrículasDispPorPerDto;
import com.figueiras.photocontest.backend.rest.dtos.VehiculoDto;

import java.util.List;

public interface ServicioVehiculo {

    Vehiculo registrarVehiculo(VehiculoDto vehiculoDto) throws CampoDuplicadoException, InstanceNotFoundException; /*** US28 */
    List<String> getTodasMatriculas(); /*** US27 */
    List<MatrículasDispPorPerDto> getMatriculasByPer();
    Marca registrarMarca(String nombre, String descripcion);
    Modelo registrarModelo(String nombre, String descripcion);
}
