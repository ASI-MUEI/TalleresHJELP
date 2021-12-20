package com.hjelp.backend.model.services;

import com.hjelp.backend.model.entities.Usuario;
import com.hjelp.backend.model.exceptions.CampoDuplicadoException;
import com.hjelp.backend.model.exceptions.IncorrectLoginException;
import com.hjelp.backend.model.exceptions.IncorrectPasswordException;
import com.hjelp.backend.model.exceptions.InstanceNotFoundException;
import com.hjelp.backend.rest.dtos.UsuarioCambioContraseñaDto;
import com.hjelp.backend.rest.dtos.UsuarioDto;
import com.hjelp.backend.rest.dtos.UsuarioLoginDto;
import org.springframework.data.domain.Slice;

public interface ServicioUsuario {

    Usuario recuperarUsuario(String nombreUsuario) throws InstanceNotFoundException; /*** US02  */
    void registrarUsuario(UsuarioDto usuarioDto) throws CampoDuplicadoException; /*** US01, US19, US28  */
    Usuario iniciarSesionUsuario(UsuarioLoginDto usuarioLoginDto) throws IncorrectLoginException; /*** US03  */
    void cambiarContrasenaUsuario(UsuarioCambioContraseñaDto usuarioCambioContrasenaDto, boolean isFromReset) throws IncorrectPasswordException; /*** US04  */
    Usuario actualizarDatosUsuario(UsuarioDto usuarioDto) throws InstanceNotFoundException; /*** US02  */
    Slice<Usuario> findMecanicos(); /*** US06, US26  */
}