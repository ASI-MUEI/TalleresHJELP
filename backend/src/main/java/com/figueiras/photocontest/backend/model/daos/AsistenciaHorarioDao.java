package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.AsistenciaHorario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AsistenciaHorarioDao extends PagingAndSortingRepository<AsistenciaHorario, Long> {
}
