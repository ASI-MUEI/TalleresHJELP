package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.Pieza;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AsistenciaPiezaDao extends PagingAndSortingRepository<com.figueiras.photocontest.backend.model.entities.AsistenciaPieza, Long> {

    @Query("SELECT p FROM AsistenciaPieza p WHERE p.idAsistencia = :idAsistencia")
    Slice<Pieza> findPiezasByIdAsistencia(Long idAsistencia, Pageable pageable);
    @Query("SELECT p FROM AsistenciaPieza p WHERE p.idPieza = :idPieza")
    Optional<Pieza> findByIdPieza(Long idPieza);
}
