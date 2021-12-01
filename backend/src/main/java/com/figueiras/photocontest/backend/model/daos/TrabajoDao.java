package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.Asistencia;
import com.figueiras.photocontest.backend.model.entities.Trabajo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TrabajoDao extends PagingAndSortingRepository<Trabajo, Long> {
    @Query("SELECT t FROM Trabajo t WHERE t.estado.nombre LIKE %:estado%")
    Slice<Trabajo> findByEstado(String estado);
    @Query("SELECT t FROM Trabajo t ORDER BY t.fechaCreado ASC")
    Slice<Trabajo> findTrabajosOrderByFecha(Pageable pageable);
    @Query("SELECT a FROM Asistencia a WHERE a.trabajo.idTrabajo =:idTrabajo ORDER BY a.fecha ASC")
    Slice<Asistencia> findAsistenciasOrderByFecha(Long idTrabajo, Pageable pageable);
    @Query("SELECT a FROM Trabajo a WHERE a.vehiculo.idVehiculo = :idVehiculo")
    Slice<Trabajo> findByIdVehiculo(Long idVehiculo);
}
