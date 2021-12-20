package com.hjelp.backend.model.daos;

import com.hjelp.backend.model.entities.EstadoTrabajo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EstadoTrabajosDao extends PagingAndSortingRepository<EstadoTrabajo, Long> {
    @Query("SELECT et FROM EstadoTrabajo et WHERE et.nombre LIKE  %:estado%")
    Optional<EstadoTrabajo> findByNombre(String estado);
}
