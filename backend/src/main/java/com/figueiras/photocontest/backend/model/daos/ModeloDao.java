package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.Modelo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ModeloDao extends PagingAndSortingRepository<Modelo, Long> {
}
