package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.Usuario;
import com.figueiras.photocontest.backend.model.exceptions.*;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioCambioContraseñaDto;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioDto;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioLoginDto;

import java.util.List;

public interface ServicioUsuario {

    Block<Usuario> recuperarUsuarios(String nombre, int page, int size);
    List<String> recuperarNombresUsuarios();
    Usuario recuperarUsuario(String nombreUsuario) throws InstanceNotFoundException;
    void registrarUsuario(UsuarioDto usuarioDto) throws CampoDuplicadoException, CamposIntroducidosNoValidosException,
            InstanceNotFoundException;
    Usuario iniciarSesionUsuario(UsuarioLoginDto usuarioLoginDto) throws IncorrectLoginException;
    void cambiarContraseñaUsuario(UsuarioCambioContraseñaDto usuarioCambioContraseñaDto, boolean isFromReset)
            throws IncorrectPasswordException;
    Usuario actualizarDatosUsuario(UsuarioDto usuarioDto);
    void enviarEnlaceRecuperacionContrasena(String nombreUsuarioDestinatario) throws InstanceNotFoundException;
    boolean comprobarEnlaceRecuperacionContrasena(String jwt) throws InstanceNotFoundException;
    void eliminarUsuario(String nombreUsuario) throws InstanceNotFoundException;
    Usuario actualizarUsuario(Usuario usuario);
}