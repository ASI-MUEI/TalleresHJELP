package com.hjelp.backend.model.services;

import com.hjelp.backend.model.entities.Marca;
import com.hjelp.backend.model.entities.Modelo;
import com.hjelp.backend.model.entities.Vehiculo;
import com.hjelp.backend.model.exceptions.CampoDuplicadoException;
import com.hjelp.backend.model.exceptions.InstanceNotFoundException;
import com.hjelp.backend.rest.dtos.MatrículasDispPorPerDto;
import com.hjelp.backend.rest.dtos.VehiculoDto;

import java.util.List;

public interface ServicioVehiculo {

    Vehiculo registrarVehiculo(VehiculoDto vehiculoDto) throws CampoDuplicadoException, InstanceNotFoundException; /*** US28 */
    List<String> getTodasMatriculas(); /*** US27 */
    List<MatrículasDispPorPerDto> getMatriculasByPer();
    /**************Métodos para los test *****************/
    Marca registrarMarca(String nombre, String descripcion);
    Modelo registrarModelo(String nombre, String descripcion);
}
