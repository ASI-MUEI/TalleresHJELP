package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.Asistencia;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AsistenciasDao extends PagingAndSortingRepository<Asistencia, Long> {

    @Query("SELECT a FROM Asistencia a WHERE a.fecha = :fecha")
    List<Asistencia> findAsistenciasPorFecha(LocalDateTime fecha);
    @Query("SELECT a FROM Asistencia a WHERE a.trabajo.idTrabajo=:idTrabajo")
    List<Asistencia> findByIdTrabajo(Long idTrabajo);
    @Query("SELECT a FROM Asistencia a WHERE a.retrasada = 1")
    Slice<Asistencia> findRetrasadas(Pageable pageable);
}
