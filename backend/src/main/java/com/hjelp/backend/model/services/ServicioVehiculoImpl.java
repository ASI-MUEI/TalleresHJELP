package com.hjelp.backend.model.services;

import com.figueiras.photocontest.backend.model.daos.*;
import com.figueiras.photocontest.backend.model.entities.*;
import com.hjelp.backend.model.daos.*;
import com.hjelp.backend.model.entities.*;
import com.hjelp.backend.model.exceptions.CampoDuplicadoException;
import com.hjelp.backend.model.exceptions.InstanceNotFoundException;
import com.hjelp.backend.rest.dtos.MatrículasDispPorPerDto;
import com.hjelp.backend.rest.dtos.VehiculoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioVehiculoImpl implements ServicioVehiculo{

    @Autowired
    private VehiculoDao vehiculoDao;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private ModeloDao modeloDao;

    @Autowired
    private MarcaDao marcaDao;

    @Autowired
    private FlotaDao flotaDao;

    @Autowired
    private TrabajoDao trabajoDao;

    @Override
    public Vehiculo registrarVehiculo(VehiculoDto vehiculoDto) throws CampoDuplicadoException, InstanceNotFoundException {

        Optional<Usuario> userOpt = usuarioDao.findById(vehiculoDto.getUsuario());
        if (userOpt.isEmpty()){
            throw new InstanceNotFoundException("entidades.usuario.idUsuario", vehiculoDto.getUsuario());
        }

        Optional<Vehiculo> vehOptionalByMatricula = vehiculoDao.findByMatricula(vehiculoDto.getMatricula());
        Optional<Vehiculo> vehOptionalByNumBastidor = vehiculoDao.findByNumBastidor(vehiculoDto.getMatricula());
        // Se valida que la matrícula sea única
        if(vehOptionalByMatricula.isPresent()){
            throw new CampoDuplicadoException("entidades.vehiculo.matricula", vehiculoDto.getMatricula());
        }
        // Se valida que el numBastidor sea único
        if(vehOptionalByNumBastidor.isPresent()){
            throw new CampoDuplicadoException("entidades.vehiculo.numBastidor", vehiculoDto.getNumBastidor());
        }

        Optional<Modelo> vehModelo = Optional.empty();
        if (vehiculoDto.getModelo() != null) {
            vehModelo = modeloDao.findById(vehiculoDto.getModelo());
            if (vehModelo.isEmpty()) {
                throw new InstanceNotFoundException("entidades.modelo.idModelo", vehiculoDto.getModelo());
            }
        }

        Optional<Flota> vehFlota = Optional.empty();
        if (vehiculoDto.getFlota() != null) {
            vehFlota = flotaDao.findById(vehiculoDto.getFlota());
            if (vehFlota.isEmpty()) {
                throw new InstanceNotFoundException("entidades.flota.idFlota", vehiculoDto.getFlota());
            }
        }

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMatricula(vehiculoDto.getMatricula());
        vehiculo.setNumBastidor(vehiculoDto.getNumBastidor());
        vehiculo.setUsuario(userOpt.get());
        vehModelo.ifPresent(vehiculo::setModelo);
        vehFlota.ifPresent(vehiculo::setFlota);
        vehiculoDao.save(vehiculo);
        return vehiculo;
    }

    @Override
    public List<String> getTodasMatriculas() {
        return vehiculoDao.findAllMatriculas();
    }

    @Override
    public List<MatrículasDispPorPerDto> getMatriculasByPer() {
        List<Vehiculo> vehs = vehiculoDao.findMatriculasByper();
        List<MatrículasDispPorPerDto> result = new ArrayList<>();
        for (Vehiculo vehiculo : vehs){
            Slice<Trabajo> trabajos = trabajoDao.findAbiertosByIdVehiculo(vehiculo.getIdVehiculo());
            for (Trabajo trabajo: trabajos){
                result.add(new MatrículasDispPorPerDto(vehiculo.getMatricula(), trabajo.getPeritado()));
            }
        }
        return result;
    }

    @Override
    public Marca registrarMarca(String nombre, String descripcion) {
        Marca marca = new Marca();
        marca.setNombre(nombre);
        marca.setDescripcion(descripcion);
        marcaDao.save(marca);
        return marca;
    }

    @Override
    public Modelo registrarModelo(String nombre, String descripcion) {
        Modelo modelo = new Modelo();
        modelo.setNombre(nombre);
        modelo.setDescripcion(descripcion);
        modeloDao.save(modelo);
        return modelo;
    }

}
