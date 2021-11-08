package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.EstadoTrabajo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EstadoTrabajosDao extends PagingAndSortingRepository<EstadoTrabajo, Long> {
}
