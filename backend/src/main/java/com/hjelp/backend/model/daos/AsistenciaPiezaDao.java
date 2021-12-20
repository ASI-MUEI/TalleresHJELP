package com.hjelp.backend.model.daos;

import com.hjelp.backend.model.entities.AsistenciaPieza;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AsistenciaPiezaDao extends PagingAndSortingRepository<AsistenciaPieza, Long> {

    @Query("SELECT p FROM AsistenciaPieza p WHERE p.idAsistencia = :idAsistencia")
    Slice<AsistenciaPieza> findPiezasByIdAsistencia(Long idAsistencia, Pageable pageable);
    @Query("SELECT p FROM AsistenciaPieza p WHERE p.idPieza = :idPieza AND p.idAsistencia = :idAsistencia")
    Optional<AsistenciaPieza> findByIdPiezaIdAsistencia(Long idPieza, Long idAsistencia);
}
