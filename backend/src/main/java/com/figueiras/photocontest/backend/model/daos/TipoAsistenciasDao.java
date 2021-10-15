package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.TipoAsistencias;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TipoAsistenciasDao extends PagingAndSortingRepository<TipoAsistencias, Long> {
}
