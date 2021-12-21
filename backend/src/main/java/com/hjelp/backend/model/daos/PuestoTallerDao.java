package com.hjelp.backend.model.daos;

import com.hjelp.backend.model.entities.PuestoTaller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PuestoTallerDao extends PagingAndSortingRepository<PuestoTaller, Long> {
    @Query("SELECT pt FROM PuestoTaller pt WHERE pt.nombre LIKE  %:nombre%")
    Optional<PuestoTaller> findByNombre(String nombre);
}
