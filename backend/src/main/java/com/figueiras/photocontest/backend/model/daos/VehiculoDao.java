package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.Vehiculo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface VehiculoDao extends PagingAndSortingRepository<Vehiculo, Long> {
    @Query("SELECT v FROM Vehiculo v WHERE v.matricula LIKE %:matricula%")
    Optional<Vehiculo> findByMatricula(String matricula);
    @Query("SELECT v FROM Vehiculo v WHERE v.numBastidor LIKE %:numBastidor%")
    Optional<Vehiculo> findByNumBastidor(String numBastidor);
    @Query("SELECT v FROM Vehiculo v ORDER BY v.idVehiculo")
    List<String> findAllVehiculos();
    @Query("SELECT v.matricula FROM Vehiculo v ORDER BY v.matricula")
    List<String> findAllMatriculas();

}
