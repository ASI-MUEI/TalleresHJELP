package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.EstadoAsistencias;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EstadoAsistenciasDao extends PagingAndSortingRepository<EstadoAsistencias, Long> {
}
