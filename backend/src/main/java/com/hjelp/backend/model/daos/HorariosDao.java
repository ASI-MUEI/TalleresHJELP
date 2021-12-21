package com.hjelp.backend.model.daos;

import com.hjelp.backend.model.entities.Horarios;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;


public interface HorariosDao extends PagingAndSortingRepository<Horarios, Long>{
    @Query("SELECT h FROM Horarios h ORDER BY h.idFranjaHoraria")
    List<Horarios> getEtiquetasOrderById();

    @Query("SELECT h FROM Horarios h WHERE h.franjaHoraria = :nombre")
    Optional<Horarios> findByNombre(String nombre);
}
