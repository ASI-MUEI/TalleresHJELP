package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.Trabajo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TrabajoDao extends PagingAndSortingRepository<Trabajo, Long> {
}
