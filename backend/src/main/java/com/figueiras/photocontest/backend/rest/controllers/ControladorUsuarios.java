package com.figueiras.photocontest.backend.rest.controllers;

import com.figueiras.photocontest.backend.model.entities.Usuario;
import com.figueiras.photocontest.backend.model.exceptions.*;
import com.figueiras.photocontest.backend.model.services.Block;
import com.figueiras.photocontest.backend.model.services.ServicioUsuario;
import com.figueiras.photocontest.backend.rest.common.JwtGenerator;
import com.figueiras.photocontest.backend.rest.common.JwtInfo;
import com.figueiras.photocontest.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalogo-usuarios")
public class ControladorUsuarios {

    @Autowired
    private ServicioUsuario servicioUsuario;

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private MessageSource messageSource;

    private static String INCORRECT_LOGIN_EXCEPTION_CODIGO = "project.exceptions.IncorrectLoginException";
    private static String INCORRECT_PASSWORD_EXCEPTION_CODIGO = "project.exceptions.IncorrectPasswordException";

    @ExceptionHandler(IncorrectLoginException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErroresDto manejarExcepcionIncorrectLoginException(IncorrectLoginException e, Locale locale){

        String mensajeExcepcion = messageSource.getMessage(INCORRECT_LOGIN_EXCEPTION_CODIGO,
               null, INCORRECT_LOGIN_EXCEPTION_CODIGO, locale);

        return  new ErroresDto(mensajeExcepcion);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErroresDto manejarExcepcionIncorrectPasswordException(IncorrectPasswordException e, Locale locale){

        String mensajeExcepcion = messageSource.getMessage(INCORRECT_PASSWORD_EXCEPTION_CODIGO,
                null, INCORRECT_PASSWORD_EXCEPTION_CODIGO, locale);

        return  new ErroresDto(mensajeExcepcion);
    }

    @GetMapping("/usuarios")
    public Block<UsuarioTablaDto> buscarUsuarios(@RequestParam(required = false) String nombreUsuario,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size){
        Block<Usuario> blockUsuarios = servicioUsuario.recuperarUsuarios(nombreUsuario, page, size);

        Block<UsuarioTablaDto> usuarioDtoBlock =
                new Block<>(UsuarioConversor.toUsuariosTablaDto(blockUsuarios.getItems()),
                        blockUsuarios.getExistMoreItems());

        return usuarioDtoBlock;
    }

    @GetMapping("/usuarios/nombresUsuario")
    public List<String> buscarUsuarios(){

        List<String> nombresDeUsuario = servicioUsuario.recuperarNombresUsuarios();
        return nombresDeUsuario;
    }

    @GetMapping("/usuarios/{nombreUsuario}")
    public UsuarioDto buscarUsuario(@PathVariable String nombreUsuario) throws InstanceNotFoundException {
        Usuario usuario = servicioUsuario.recuperarUsuario(nombreUsuario);

        return UsuarioConversor.toUsuarioDto(usuario);
    }

    @PostMapping("/usuarios/{nombreUsuario}/cambio-contrasena")
    public void cambioContraseña(@RequestBody UsuarioCambioContraseñaDto usuarioCambioContraseñaDto,
                                @RequestParam(defaultValue = "false") boolean isFromReset)
            throws IncorrectPasswordException {

        servicioUsuario.cambiarContraseñaUsuario(usuarioCambioContraseñaDto, isFromReset);
    }

    @PostMapping("/registrarse")
    public ResponseEntity registrarUsuario(@RequestBody UsuarioDto usuarioDto)
            throws CampoDuplicadoException, CamposIntroducidosNoValidosException,
            InstanceNotFoundException {

        servicioUsuario.registrarUsuario(usuarioDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/iniciar-sesion")
    public UsuarioAutenticadoDto iniciarSesion(@RequestBody UsuarioLoginDto usuarioLoginDto)
            throws IncorrectLoginException {

        Usuario usuario = servicioUsuario.iniciarSesionUsuario(usuarioLoginDto);
        String jwt = generateServiceToken(usuario);

        return UsuarioConversor.toUsuarioAutenticadoDto(usuario, jwt);
    }

    @PutMapping("/usuarios/{nombreUsuario}")
    public UsuarioDto actualizarDatosUsuario(@RequestBody UsuarioDto datosFormularioActualizacion){

        Usuario usuario = servicioUsuario.actualizarDatosUsuario(datosFormularioActualizacion);

        return UsuarioConversor.toUsuarioDto(usuario);
    }

    private String generateServiceToken(Usuario usuario) {

        JwtInfo jwtInfo = new JwtInfo(usuario.getIdUsuario(), usuario.getNombreUsuario(),
                usuario.getRolUsuarioSistema().toString());

        return jwtGenerator.generate(jwtInfo);
    }

    @PostMapping("/usuarios/{nombreUsuario}/recuperar-cuenta")
    public void recuperarCuenta(@PathVariable String nombreUsuario) throws InstanceNotFoundException {
        servicioUsuario.enviarEnlaceRecuperacionContrasena(nombreUsuario);
    }

    @GetMapping("/usuarios/{nombreUsuario}/restablecer-contrasena/{token}")
    public boolean esElTokenDeRestablecerContraseñaCorrecto(@PathVariable String token)
            throws InstanceNotFoundException{

        return servicioUsuario.comprobarEnlaceRecuperacionContrasena(token);
    }

    @PostMapping("/usuarios/{nombreUsuario}/eliminar-cuenta")
    public void eliminarCuenta(@PathVariable String nombreUsuario) throws InstanceNotFoundException {
        servicioUsuario.eliminarUsuario(nombreUsuario);
    }
}