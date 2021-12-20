package com.figueiras.photocontest.backend.test.model.services;

import com.figueiras.photocontest.backend.model.daos.MarcaDao;
import com.figueiras.photocontest.backend.model.daos.ModeloDao;
import com.figueiras.photocontest.backend.model.daos.UsuarioDao;
import com.figueiras.photocontest.backend.model.daos.VehiculoDao;
import com.figueiras.photocontest.backend.model.entities.Marca;
import com.figueiras.photocontest.backend.model.entities.Modelo;
import com.figueiras.photocontest.backend.model.entities.Usuario;
import com.figueiras.photocontest.backend.model.entities.Vehiculo;
import com.figueiras.photocontest.backend.model.exceptions.CampoDuplicadoException;
import com.figueiras.photocontest.backend.model.exceptions.CamposIntroducidosNoValidosException;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.model.services.ServicioVehiculo;
import com.figueiras.photocontest.backend.rest.dtos.VehiculoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ServiceVehiculoTest {

    @Autowired
    ServicioVehiculo servicioVehiculo;

    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    MarcaDao marcaDao;

    @Autowired
    ModeloDao modeloDao;

    @Autowired
    VehiculoDao vehiculoDao;

    private Usuario registrarUsuario() throws CampoDuplicadoException, CamposIntroducidosNoValidosException, InstanceNotFoundException {
        Usuario user = new Usuario();
        user.setNombreUsuario("Laura");
        user.setApellidosUsuario("Insua Regueiro");
        user.setCorreoElectronicoUsuario("laura@gmail.com");
        user.setDni("48177777V");
        user.setNombrePilaUsuario("laura");
        user.setContrasenaUsuario("123123");
        user = usuarioDao.save(user);
        return user;
    }

    private Marca registrarMarca(){
        return marcaDao.save(new Marca(1L,"Ford", "Descripcion Ford"));
    }

    private Modelo registrarModelo(Marca marca){
        return modeloDao.save(new Modelo(1L, "Ford focus", "Descripcion Ford",marca, ""));
    }

    /*** US28 */
    @Test
    public void registrarVehiculoTest() throws CampoDuplicadoException, CamposIntroducidosNoValidosException, InstanceNotFoundException {
        Usuario user = registrarUsuario();
        Marca marca = registrarMarca();

        VehiculoDto vehiculo = new VehiculoDto();;
        vehiculo.setMatricula("6564GMP");
        vehiculo.setMarca(marca.getIdMarca());
        vehiculo.setUsuario(user.getIdUsuario());
        vehiculo.setNumBastidor("542254254");
        vehiculo.setModelo(registrarModelo(marca).getIdModelo());
        Vehiculo veh = servicioVehiculo.registrarVehiculo(vehiculo);

        assertEquals(veh, vehiculoDao.findById(veh.getIdVehiculo()).get());

    }

    /*** US28 */
    @Test
    public void registrarVehiculoTestExceptionCampoDuplicado() throws CampoDuplicadoException, CamposIntroducidosNoValidosException, InstanceNotFoundException {
        Usuario user = registrarUsuario();
        Marca marca = registrarMarca();

        VehiculoDto vehiculo = new VehiculoDto();;
        vehiculo.setMatricula("6564GMP");
        vehiculo.setMarca(marca.getIdMarca());
        vehiculo.setUsuario(user.getIdUsuario());
        vehiculo.setNumBastidor("542254254");
        vehiculo.setModelo(registrarModelo(marca).getIdModelo());
        Vehiculo veh = servicioVehiculo.registrarVehiculo(vehiculo);

        VehiculoDto vehiculo1 = new VehiculoDto();
        vehiculo1.setMatricula("6564GMP");
        vehiculo1.setMarca(marca.getIdMarca());
        vehiculo1.setUsuario(user.getIdUsuario());
        vehiculo1.setNumBastidor("542254254");
        vehiculo1.setModelo(registrarModelo(marca).getIdModelo());

        assertThrows(CampoDuplicadoException.class, () ->  servicioVehiculo.registrarVehiculo(vehiculo1));

    }

    /*** US28 */
    @Test
    public void registrarVehiculoTestExceptionInstanceNotFound() throws CampoDuplicadoException, CamposIntroducidosNoValidosException, InstanceNotFoundException {
        VehiculoDto vehiculo = new VehiculoDto();
        Marca marca = registrarMarca();

        vehiculo.setMatricula("6564GMP");
        vehiculo.setMarca(marca.getIdMarca());
        vehiculo.setIdVehiculo(1L);
        vehiculo.setUsuario(1L);
        vehiculo.setNumBastidor("542254254");
        vehiculo.setModelo(registrarModelo(marca).getIdModelo());

        assertThrows(InstanceNotFoundException.class, () -> servicioVehiculo.registrarVehiculo(vehiculo));

    }

    /*** US27 */
    @Test
    public void getTodasMatriculasTest() throws CampoDuplicadoException, CamposIntroducidosNoValidosException, InstanceNotFoundException {
        Usuario user = registrarUsuario();
        Marca marca = registrarMarca();

        VehiculoDto vehiculo = new VehiculoDto();;
        vehiculo.setMatricula("6564GMT");
        vehiculo.setMarca(marca.getIdMarca());
        vehiculo.setIdVehiculo(1L);
        vehiculo.setUsuario(user.getIdUsuario());
        vehiculo.setNumBastidor("54225254");
        vehiculo.setModelo(registrarModelo(marca).getIdModelo());
        Vehiculo veh = servicioVehiculo.registrarVehiculo(vehiculo);

        VehiculoDto vehiculo1 = new VehiculoDto();;
        vehiculo1.setMatricula("6564GMP");
        vehiculo1.setMarca(marca.getIdMarca());
        vehiculo1.setIdVehiculo(1L);
        vehiculo1.setUsuario(user.getIdUsuario());
        vehiculo1.setNumBastidor("542254254");
        vehiculo1.setModelo(registrarModelo(marca).getIdModelo());
        Vehiculo veh1 = servicioVehiculo.registrarVehiculo(vehiculo1);

        List<String> matriculas = servicioVehiculo.getTodasMatriculas();

        assertEquals(matriculas.get(0), vehiculo1.getMatricula());
        assertEquals(matriculas.get(1), vehiculo.getMatricula());
        assertEquals(matriculas.size(), 2);
    }

}