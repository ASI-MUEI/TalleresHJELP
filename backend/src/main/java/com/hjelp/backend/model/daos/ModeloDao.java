package com.hjelp.backend.model.daos;

import com.hjelp.backend.model.entities.Modelo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ModeloDao extends PagingAndSortingRepository<Modelo, Long> {
    @Query("SELECT m FROM Modelo m WHERE m.nombre LIKE  %:nombre%")
    Optional<Modelo> findByNombre(String nombre);
}
