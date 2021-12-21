package com.hjelp.backend.model.daos;

import com.hjelp.backend.model.entities.TipoAsistencias;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TipoAsistenciasDao extends PagingAndSortingRepository<TipoAsistencias, Long> {

    @Query("SELECT t FROM TipoAsistencias t WHERE t.nombre LIKE  %:tipo%")
    Optional<TipoAsistencias> findByNombre(String tipo);
}
