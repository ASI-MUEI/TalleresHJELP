package com.hjelp.backend.test.model.services;

import com.hjelp.backend.model.daos.MarcaDao;
import com.hjelp.backend.model.daos.ModeloDao;
import com.hjelp.backend.model.daos.UsuarioDao;
import com.hjelp.backend.model.daos.VehiculoDao;
import com.hjelp.backend.model.entities.RolUsuarioSistema;
import com.hjelp.backend.model.entities.Usuario;
import com.hjelp.backend.model.exceptions.*;
import com.hjelp.backend.model.services.ServicioUsuario;
import com.hjelp.backend.rest.dtos.UsuarioCambioContraseñaDto;
import com.hjelp.backend.rest.dtos.UsuarioDto;
import com.hjelp.backend.rest.dtos.UsuarioLoginDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ServiceUsuarioTest {

    @Autowired
    ServicioUsuario servicioUsuario;

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

     /*** US02  */
     @Test
     public void recuperarUsuarioTest() throws InstanceNotFoundException, CampoDuplicadoException, CamposIntroducidosNoValidosException {
        Usuario user = registrarUsuario();
        assertEquals(user, servicioUsuario.recuperarUsuario(user.getNombreUsuario()));
    }

    /*** US02  */
    @Test
    public void recuperarUsuarioNotFound(){
        assertThrows(InstanceNotFoundException.class, () -> servicioUsuario.recuperarUsuario("Laura"));
    }

    /*** US02  */
    @Test
    public void actualizarDatosUsuarioTest() throws InstanceNotFoundException, CampoDuplicadoException, CamposIntroducidosNoValidosException {
        Usuario userPrimero = registrarUsuario();

        UsuarioDto user = new UsuarioDto();
        user.setApellidosUsuario(userPrimero.getApellidosUsuario());
        user.setDni(userPrimero.getDni());
        user.setNombreUsuario(userPrimero.getNombreUsuario());
        user.setNombrePilaUsuario("Paula");
        user.setEmail(userPrimero.getCorreoElectronicoUsuario());
        user.setRolUsuario(userPrimero.getRolUsuarioSistema().toString());
        Usuario usuarioAct = servicioUsuario.actualizarDatosUsuario(user);

        assertEquals(userPrimero.getIdUsuario(), usuarioAct.getIdUsuario());
        assertEquals(usuarioAct.getNombrePilaUsuario(), "Paula");
        assertEquals(userPrimero.getApellidosUsuario(), usuarioAct.getApellidosUsuario());
        assertEquals(userPrimero.getDni(), usuarioAct.getDni());
        assertEquals(userPrimero.getCorreoElectronicoUsuario(), usuarioAct.getCorreoElectronicoUsuario());
        assertEquals(userPrimero.getRolUsuarioSistema(), usuarioAct.getRolUsuarioSistema());
    }

    /*** US02  */
    @Test
    public void actualizarDatosUsuarioNotFound() {
        UsuarioDto user = new UsuarioDto();
        user.setNombreUsuario("Paula");
        assertThrows(InstanceNotFoundException.class, () -> servicioUsuario.actualizarDatosUsuario(user));
    }

    /*** US03  */
    @Test
    public void iniciarSesionUsuarioTest() throws IncorrectLoginException, CampoDuplicadoException, CamposIntroducidosNoValidosException, InstanceNotFoundException {
        UsuarioDto user = new UsuarioDto();
        user.setApellidosUsuario("Insua Regueiro");
        user.setDni("48115948V");
        user.setNombreUsuario("Laura");
        user.setNombrePilaUsuario("Paula");
        user.setEmail("laura@udc.es");
        user.setRolUsuario("0");
        user.setContraseña("123123");
        servicioUsuario.registrarUsuario(user);
        UsuarioLoginDto usuario = new UsuarioLoginDto("Laura", "123123");
        Usuario usuarioLeguado = servicioUsuario.iniciarSesionUsuario(usuario);

        assertEquals(usuarioLeguado.getNombreUsuario(), usuarioLeguado.getNombreUsuario());
    }

    /*** US03  */
    @Test
    public void iniciarSesionUsuarioIncorrectException() throws CampoDuplicadoException, CamposIntroducidosNoValidosException, InstanceNotFoundException {
        Usuario userPrimero = registrarUsuario();
        UsuarioLoginDto usuario = new UsuarioLoginDto(userPrimero.getNombreUsuario(), "123");

        assertThrows(IncorrectLoginException.class, () -> servicioUsuario.iniciarSesionUsuario(usuario));
    }

    /*** US04  */
    @Test
    public void cambiarContrasenaUsuarioTest() throws IncorrectPasswordException, CampoDuplicadoException {
        UsuarioDto user = new UsuarioDto();
        user.setApellidosUsuario("Insua Regueiro");
        user.setDni("48115948V");
        user.setNombreUsuario("Laura");
        user.setNombrePilaUsuario("Paula");
        user.setEmail("laura@udc.es");
        user.setRolUsuario("0");
        user.setContraseña("123123");
        servicioUsuario.registrarUsuario(user);

        UsuarioCambioContraseñaDto usuario = new UsuarioCambioContraseñaDto();
        usuario.setNombreUsuario(user.getNombreUsuario());
        usuario.setContraseñaAntigua(user.getContraseña());
        usuario.setContraseñaNueva("123");

        servicioUsuario.cambiarContrasenaUsuario(usuario, false);
    }

    /*** US04  */
    @Test
    public void cambiarContrasenaUsuarioIncorrectPasswd() throws CampoDuplicadoException {
        UsuarioDto user = new UsuarioDto();
        user.setApellidosUsuario("Insua Regueiro");
        user.setDni("48115948V");
        user.setNombreUsuario("Laura");
        user.setNombrePilaUsuario("Paula");
        user.setEmail("laura@udc.es");
        user.setRolUsuario("0");
        user.setContraseña("123123");
        servicioUsuario.registrarUsuario(user);

        UsuarioCambioContraseñaDto usuario = new UsuarioCambioContraseñaDto();
        usuario.setNombreUsuario(user.getNombreUsuario());
        usuario.setContraseñaAntigua("123");
        usuario.setContraseñaNueva("123");

        assertThrows(IncorrectPasswordException.class, () -> servicioUsuario.cambiarContrasenaUsuario(usuario, false));
    }

    /*** US01, US19, US28  */
    @Test
    public void registrarUsuarioTest() throws CampoDuplicadoException{
        UsuarioDto user = new UsuarioDto();
        user.setApellidosUsuario("Insua Regueiro");
        user.setDni("48115948V");
        user.setNombreUsuario("Laura");
        user.setNombrePilaUsuario("Paula");
        user.setEmail("laura@udc.es");
        user.setRolUsuario("0");
        user.setContraseña("123123");
        servicioUsuario.registrarUsuario(user);

        Optional<Usuario> us = usuarioDao.findByCorreoElectronicoUsuario(user.getEmail());
        assertEquals(user.getNombreUsuario(),  us.get().getNombreUsuario());


    }

    /*** US01, US19, US28  */
    @Test
    public void registrarUsuarioCampoDuplicado() throws CampoDuplicadoException{
        UsuarioDto user = new UsuarioDto();
        user.setApellidosUsuario("Insua Regueiro");
        user.setDni("48115948V");
        user.setNombreUsuario("Laura");
        user.setNombrePilaUsuario("Paula");
        user.setEmail("laura@udc.es");
        user.setRolUsuario("0");
        user.setContraseña("123123");
        servicioUsuario.registrarUsuario(user);

        UsuarioDto user1 = new UsuarioDto();
        user1.setApellidosUsuario("Insua Regueiro");
        user1.setDni("48115948V");
        user1.setNombreUsuario("Laura");
        user1.setNombrePilaUsuario("Paula");
        user1.setEmail("laura@udc.es");
        user1.setRolUsuario("0");
        user1.setContraseña("123123");
        assertThrows(CampoDuplicadoException.class, () -> servicioUsuario.registrarUsuario(user)) ;
    }

    /*** US06, US26  */
    @Test
    public void findMecanicosTest(){
        Usuario user = new Usuario();
        user.setNombreUsuario("Laura");
        user.setApellidosUsuario("Insua Regueiro");
        user.setCorreoElectronicoUsuario("laura@gmail.com");
        user.setDni("48177777V");
        user.setNombrePilaUsuario("laura");
        user.setContrasenaUsuario("123123");
        user.setRolUsuarioSistema(RolUsuarioSistema.MECANICO);
        user = usuarioDao.save(user);

        Usuario user1 = new Usuario();
        user1.setNombreUsuario("Lauri");
        user1.setApellidosUsuario("Insua Regueiro");
        user1.setCorreoElectronicoUsuario("lauri@gmail.com");
        user1.setDni("48137777V");
        user1.setNombrePilaUsuario("lausra");
        user1.setContrasenaUsuario("123123");
        user1.setRolUsuarioSistema(RolUsuarioSistema.MECANICO);
        user1 = usuarioDao.save(user1);

        Slice<Usuario> usuarios = servicioUsuario.findMecanicos();
        List<Usuario> mecanicos = usuarios.getContent();
        assertEquals(2, mecanicos.size());
        assertEquals("laura@gmail.com", (mecanicos.get(0).getCorreoElectronicoUsuario()));
        assertEquals("lauri@gmail.com", (mecanicos.get(1).getCorreoElectronicoUsuario()));

    }

    /*** US06, US26  */
    @Test
    public void findMecanicosSize0Test(){
        Usuario user = new Usuario();
        user.setNombreUsuario("Laura");
        user.setApellidosUsuario("Insua Regueiro");
        user.setCorreoElectronicoUsuario("laura@gmail.com");
        user.setDni("48177777V");
        user.setNombrePilaUsuario("laura");
        user.setContrasenaUsuario("123123");
        user.setRolUsuarioSistema(RolUsuarioSistema.CLIENTE);
        user = usuarioDao.save(user);

        Slice<Usuario> usuarios = servicioUsuario.findMecanicos();
        List<Usuario> mecanicos = usuarios.getContent();
        assertEquals(0, mecanicos.size());

    }



}