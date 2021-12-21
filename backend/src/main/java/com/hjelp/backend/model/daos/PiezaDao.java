package com.hjelp.backend.model.daos;

import com.hjelp.backend.model.entities.Pieza;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PiezaDao extends PagingAndSortingRepository<Pieza, Long> {
    @Query("SELECT p FROM Pieza p WHERE p.nombre LIKE  %:nombre%")
    Optional<Pieza> findByNombre(String nombre);
}
