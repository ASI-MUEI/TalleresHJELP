package com.hjelp.backend.model.daos;

import com.hjelp.backend.model.entities.Flota;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FlotaDao extends PagingAndSortingRepository<Flota, Long> {
}
