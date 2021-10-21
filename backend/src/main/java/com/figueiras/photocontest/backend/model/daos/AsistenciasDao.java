package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.Asistencia;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AsistenciasDao extends PagingAndSortingRepository<Asistencia, Long> {
}
