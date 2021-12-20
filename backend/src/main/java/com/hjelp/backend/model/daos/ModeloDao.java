package com.hjelp.backend.model.daos;

import com.hjelp.backend.model.entities.Modelo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ModeloDao extends PagingAndSortingRepository<Modelo, Long> {
}
