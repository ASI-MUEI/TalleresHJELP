package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.Flota;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FlotaDao extends PagingAndSortingRepository<Flota, Long> {
}
