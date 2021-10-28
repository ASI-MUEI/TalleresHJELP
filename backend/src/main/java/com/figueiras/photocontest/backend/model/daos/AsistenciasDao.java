package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.Asistencia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface AsistenciasDao extends PagingAndSortingRepository<Asistencia, Long> {

    @Query("SELECT a FROM Asistencia a WHERE a.fecha = :fecha")
    List<Asistencia> findAsistenciasPorFecha(Date fecha);
}
