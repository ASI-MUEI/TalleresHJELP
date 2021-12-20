package com.hjelp.backend.rest.conversor;


import com.hjelp.backend.model.entities.Flota;
import com.hjelp.backend.model.entities.Marca;
import com.hjelp.backend.model.entities.Modelo;
import com.hjelp.backend.model.entities.Vehiculo;
import com.hjelp.backend.rest.dtos.FlotaDto;
import com.hjelp.backend.rest.dtos.MarcaDto;
import com.hjelp.backend.rest.dtos.ModeloDto;
import com.hjelp.backend.rest.dtos.VehiculoCompletoDto;

public class VehiculoConversor {
    public static VehiculoCompletoDto toVehiculoDto(Vehiculo vehiculo){
        VehiculoCompletoDto vehiculoDto = new VehiculoCompletoDto();
        vehiculoDto.setIdVehiculo(vehiculo.getIdVehiculo());
        vehiculoDto.setMatricula(vehiculo.getMatricula());
        vehiculoDto.setModelo(toModeloDto(vehiculo.getModelo()));
        vehiculoDto.setFlota(toFlotaDto(vehiculo.getFlota()));
        vehiculoDto.setNumBastidor(vehiculo.getNumBastidor());
        return vehiculoDto;
    }

    public static MarcaDto toMarcaDto(Marca marca){
        MarcaDto marcaDto = new MarcaDto();
        marcaDto.setIdMarca(marca.getIdMarca());
        marcaDto.setNombre(marca.getNombre());
        marcaDto.setDescripcion(marca.getDescripcion());
        return marcaDto;
    }

    public static ModeloDto toModeloDto(Modelo modelo){
        ModeloDto modeloDto = new ModeloDto();
        modeloDto.setIdModelo(modelo.getIdModelo());
        modeloDto.setNombre(modelo.getNombre());
        modeloDto.setMarca(toMarcaDto(modelo.getMarca()));
        modeloDto.setDescripcion(modelo.getDescripcion());
        return modeloDto;
    }

    public static FlotaDto toFlotaDto(Flota flota){
        FlotaDto flotaDto = new FlotaDto();
        flotaDto.setIdFlota(flota.getIdFlota());
        flota.setNombre(flota.getNombre());
        flota.setDescripcion(flota.getDescripcion());
        return flotaDto;
    }
}
