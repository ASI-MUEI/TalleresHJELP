package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.PuestoTaller;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PuestoTallerDao extends PagingAndSortingRepository<PuestoTaller, Long> {
}
