package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.Horarios;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface HorariosDao extends PagingAndSortingRepository<Horarios, Long>{
    @Query("SELECT h FROM Horarios h ORDER BY h.idFranjaHoraria")
    Slice<Horarios> getEtiquetasOrderById(Pageable pageable);
}
