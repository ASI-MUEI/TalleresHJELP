package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.Trabajo;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TrabajoDao extends PagingAndSortingRepository<Trabajo, Long> {
    @Query("SELECT t FROM Trabajo t WHERE t.estado.nombre LIKE %:estado%")
    Slice<Trabajo> findByEstado(String estado);
}
