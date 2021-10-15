package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.PlanHorarios;
import com.figueiras.photocontest.backend.model.services.Block;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlanHorariosDao extends PagingAndSortingRepository<PlanHorarios, Long> {
    @Query("SELECT ph FROM PlanHorarios ph WHERE ph.asistencia.idAsistencia = :idAsistencia")
    Block<PlanHorarios> findByIdAsistencia(Long idAsistencia);
}
