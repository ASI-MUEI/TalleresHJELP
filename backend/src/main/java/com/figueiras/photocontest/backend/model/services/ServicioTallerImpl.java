package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.daos.PuestoTallerDao;
import com.figueiras.photocontest.backend.model.daos.PuestoTallerVehiculoDao;
import com.figueiras.photocontest.backend.model.daos.VehiculoDao;
import com.figueiras.photocontest.backend.model.entities.PuestoTaller;
import com.figueiras.photocontest.backend.model.entities.PuestoTallerVehiculo;
import com.figueiras.photocontest.backend.model.entities.Vehiculo;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.model.exceptions.ParseFormatException;
import com.figueiras.photocontest.backend.rest.dtos.PuestoTallerVehiculoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.text.SimpleDateFormat;
import java.util.Optional;

public class ServicioTallerImpl implements ServicioTaller{

    @Autowired
    private PuestoTallerDao puestoTallerDao;

    @Autowired
    private VehiculoDao vehiculoDao;

    @Autowired
    private PuestoTallerVehiculoDao puestoTallerVehiculoDao;

    @Override
    public void asignarVehiculoPuesto(PuestoTallerVehiculoDto puestoTallerVehDto) throws InstanceNotFoundException,ParseFormatException {
        Long puesto = puestoTallerVehDto.getIdPuesto();
        Long vehiculo = puestoTallerVehDto.getIdPuesto();
        String fechaComienzo = puestoTallerVehDto.getFechaComienzo();
        String fechaFinal = puestoTallerVehDto.getFechaFinal();

        Optional<Vehiculo> vehOptional = vehiculoDao.findById(vehiculo);
        if(vehOptional.isEmpty()){
            throw new InstanceNotFoundException("entidades.vehiculo.idVehiculo", vehiculo);
        }

        Optional<PuestoTaller> puestoOptional = puestoTallerDao.findById(puesto);
        if(puestoOptional.isEmpty()){
            throw new InstanceNotFoundException("entidades.puestoTaller.puesto", puesto);
        }

        SimpleDateFormat sdf = new SimpleDateFormat();
        try{
            sdf.parse(fechaComienzo);
        }catch (Exception e){
            throw new ParseFormatException(fechaComienzo);
        }

        try{
            sdf.parse(fechaFinal);
        }catch (Exception e){
            throw new ParseFormatException(fechaComienzo);
        }

        PuestoTallerVehiculo ptVehiculo = new PuestoTallerVehiculo();
        ptVehiculo.setVehiculo(vehOptional.get());
        ptVehiculo.setPuesto(puestoOptional.get());
        ptVehiculo.setFechaComienzo(fechaComienzo);
        ptVehiculo.setFechaFinal(fechaFinal);
        puestoTallerVehiculoDao.save(ptVehiculo);



    }

    @Override
    public Block<PuestoTallerVehiculo> findAllPuestosTVehiculos(int page, int size) {
        Slice<PuestoTallerVehiculo> puestosVehiculos = puestoTallerVehiculoDao.findAllByDay(PageRequest.of(page, size));

        return new Block<>(puestosVehiculos.getContent(), puestosVehiculos.hasNext());
    }
}
