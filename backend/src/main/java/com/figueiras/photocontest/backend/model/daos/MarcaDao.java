package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.Marca;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MarcaDao extends PagingAndSortingRepository<Marca, Long> {
}
