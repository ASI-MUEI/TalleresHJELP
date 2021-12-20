package com.hjelp.backend.model.services;

import com.hjelp.backend.model.entities.Usuario;
import com.figueiras.photocontest.backend.model.exceptions.*;
import com.hjelp.backend.model.exceptions.*;
import com.hjelp.backend.rest.dtos.UsuarioCambioContraseñaDto;
import com.hjelp.backend.rest.dtos.UsuarioDto;
import com.hjelp.backend.rest.dtos.UsuarioLoginDto;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ServicioUsuario {

    Block<Usuario> recuperarUsuarios(String nombre, int page, int size);
    List<String> recuperarNombresUsuarios();
    Usuario recuperarUsuario(String nombreUsuario) throws InstanceNotFoundException; /*** US02  */
    void registrarUsuario(UsuarioDto usuarioDto) throws CampoDuplicadoException, CamposIntroducidosNoValidosException, InstanceNotFoundException; /*** US01, US19, US28  */
    Usuario iniciarSesionUsuario(UsuarioLoginDto usuarioLoginDto) throws IncorrectLoginException; /*** US03  */
    void cambiarContraseñaUsuario(UsuarioCambioContraseñaDto usuarioCambioContraseñaDto, boolean isFromReset) throws IncorrectPasswordException; /*** US04  */
    Usuario actualizarDatosUsuario(UsuarioDto usuarioDto); /*** US02  */
    void enviarEnlaceRecuperacionContrasena(String nombreUsuarioDestinatario) throws InstanceNotFoundException; /*** US04  */
    boolean comprobarEnlaceRecuperacionContrasena(String jwt) throws InstanceNotFoundException; /*** US04  */
    void eliminarUsuario(String nombreUsuario) throws InstanceNotFoundException;
    Usuario actualizarUsuario(Usuario usuario);
    Slice<Usuario> findMecanicos(); /*** US06, US26  */
}