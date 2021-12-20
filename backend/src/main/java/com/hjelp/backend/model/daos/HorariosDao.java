package com.hjelp.backend.model.daos;

import com.hjelp.backend.model.entities.Horarios;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface HorariosDao extends PagingAndSortingRepository<Horarios, Long>{
    @Query("SELECT h FROM Horarios h ORDER BY h.idFranjaHoraria")
    List<Horarios> getEtiquetasOrderById();
}
