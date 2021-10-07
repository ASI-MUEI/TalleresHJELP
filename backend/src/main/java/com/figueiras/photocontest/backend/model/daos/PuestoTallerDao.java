package com.figueiras.photocontest.backend.model.daos;

import com.figueiras.photocontest.backend.model.entities.PuestoTaller;
import com.figueiras.photocontest.backend.model.entities.Vehiculo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PuestoTallerDao extends PagingAndSortingRepository<PuestoTaller, Long> {
    @Query("SELECT pt FROM PuestoTaller pt WHERE pt.vehiculo = %:vehiculo%")
    Slice<Vehiculo> findByVehiculo(Long vehiculo, Pageable pageable);
}
