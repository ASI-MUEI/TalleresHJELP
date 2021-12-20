package com.hjelp.backend.model.daos;

import com.hjelp.backend.model.entities.Pieza;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PiezaDao extends PagingAndSortingRepository<Pieza, Long> {
}
