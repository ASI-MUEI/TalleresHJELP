package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.PuestoTallerVehiculo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PuestoTallerVehiculoDao extends PagingAndSortingRepository<PuestoTallerVehiculo, Long> {
    @Query("SELECT ptv FROM PuestoTallerVehiculo ptv ORDER BY ptv.puesto.idPuestoTaller, ptv.fechaComienzo")
    Slice<PuestoTallerVehiculo> findAllByDay(Pageable pageable);
}
