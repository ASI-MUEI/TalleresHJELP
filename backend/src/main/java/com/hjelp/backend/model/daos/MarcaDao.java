package com.hjelp.backend.model.daos;

import com.hjelp.backend.model.entities.Marca;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MarcaDao extends PagingAndSortingRepository<Marca, Long> {
}
