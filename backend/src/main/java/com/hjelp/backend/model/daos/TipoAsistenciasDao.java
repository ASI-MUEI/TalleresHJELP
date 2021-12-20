package com.hjelp.backend.model.daos;

import com.hjelp.backend.model.entities.TipoAsistencias;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TipoAsistenciasDao extends PagingAndSortingRepository<TipoAsistencias, Long> {
}
