package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.Pieza;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PiezaDao extends PagingAndSortingRepository<Pieza, Long> {
}
