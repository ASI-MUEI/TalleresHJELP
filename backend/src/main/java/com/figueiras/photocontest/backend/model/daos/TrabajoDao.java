package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.Asistencia;
import com.figueiras.photocontest.backend.model.entities.Trabajo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface TrabajoDao extends PagingAndSortingRepository<Trabajo, Long> {
}
