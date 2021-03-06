package com.hjelp.backend.rest.controllers;

import com.hjelp.backend.model.entities.Usuario;
import com.hjelp.backend.model.exceptions.CampoDuplicadoException;
import com.hjelp.backend.model.exceptions.IncorrectLoginException;
import com.hjelp.backend.model.exceptions.IncorrectPasswordException;
import com.hjelp.backend.model.exceptions.InstanceNotFoundException;
import com.hjelp.backend.model.services.ServicioUsuario;
import com.hjelp.backend.rest.common.JwtGenerator;
import com.hjelp.backend.rest.common.JwtInfo;
import com.hjelp.backend.rest.conversor.UsuarioConversor;
import com.hjelp.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

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

    /***
     * US01, US19, US28
     */
    @PostMapping("/registrarse")
    public ResponseEntity registrarUsuario(@RequestBody UsuarioDto usuarioDto)
            throws CampoDuplicadoException{

        servicioUsuario.registrarUsuario(usuarioDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    /***
     * US02
     */
    @PutMapping("/usuarios/{nombreUsuario}")
    public UsuarioDto actualizarDatosUsuario(@RequestBody UsuarioDto datosFormularioActualizacion) throws InstanceNotFoundException {

        Usuario usuario = servicioUsuario.actualizarDatosUsuario(datosFormularioActualizacion);

        return UsuarioConversor.toUsuarioDto(usuario);
    }

    /***
     * US02
     */
    @GetMapping("/usuarios/{nombreUsuario}")
    public UsuarioDto buscarUsuario(@PathVariable String nombreUsuario) throws InstanceNotFoundException {
        Usuario usuario = servicioUsuario.recuperarUsuario(nombreUsuario);

        return UsuarioConversor.toUsuarioDto(usuario);
    }

    /***
     * US03
     */
    @PostMapping("/iniciar-sesion")
    public UsuarioAutenticadoDto iniciarSesion(@RequestBody UsuarioLoginDto usuarioLoginDto)
            throws IncorrectLoginException {

        Usuario usuario = servicioUsuario.iniciarSesionUsuario(usuarioLoginDto);
        String jwt = generateServiceToken(usuario);

        return UsuarioConversor.toUsuarioAutenticadoDto(usuario, jwt);
    }

    /***
     * US03
     */
    private String generateServiceToken(Usuario usuario) {

        JwtInfo jwtInfo = new JwtInfo(usuario.getIdUsuario(), usuario.getNombreUsuario(),
                usuario.getRolUsuarioSistema().toString());

        return jwtGenerator.generate(jwtInfo);
    }


    /***
     * US04
     */
    @PostMapping("/usuarios/{nombreUsuario}/cambio-contrasena")
    public void cambioContrase??a(@RequestBody UsuarioCambioContrase??aDto usuarioCambioContrase??aDto,
                                 @RequestParam(defaultValue = "false") boolean isFromReset)
            throws IncorrectPasswordException {

        servicioUsuario.cambiarContrasenaUsuario(usuarioCambioContrase??aDto, isFromReset);
    }

    /***
     * US06, US26
     */
    @GetMapping("/usuarios/mecanicos")
    public List<MecanicoDto> getMecanicos(){
        return UsuarioConversor.toMecanicoDto(servicioUsuario.findMecanicos());
    }

    /*******************************************************************************************************************************/

    /*@GetMapping("/usuarios")
    public Block<UsuarioTablaDto> buscarUsuarios(@RequestParam(required = false) String nombreUsuario,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size){
        Block<Usuario> blockUsuarios = servicioUsuario.recuperarUsuarios(nombreUsuario, page, size);

        return new Block<>(UsuarioConversor.toUsuariosTablaDto(blockUsuarios.getItems()),
                blockUsuarios.getExistMoreItems());
    }

    @GetMapping("/usuarios/nombresUsuario")
    public List<String> buscarUsuarios(){

        List<String> nombresDeUsuario = servicioUsuario.recuperarNombresUsuarios();
        return nombresDeUsuario;
    }

    @PostMapping("/usuarios/{nombreUsuario}/eliminar-cuenta")
    public void eliminarCuenta(@PathVariable String nombreUsuario) throws InstanceNotFoundException {
        servicioUsuario.eliminarUsuario(nombreUsuario);
    }

    @PostMapping("/usuarios/{nombreUsuario}/recuperar-cuenta")
    public void recuperarCuenta(@PathVariable String nombreUsuario) throws InstanceNotFoundException {
        servicioUsuario.enviarEnlaceRecuperacionContrasena(nombreUsuario);
    }

    @GetMapping("/usuarios/{nombreUsuario}/restablecer-contrasena/{token}")
    public boolean esElTokenDeRestablecerContrase??aCorrecto(@PathVariable String token)
            throws InstanceNotFoundException{

        return servicioUsuario.comprobarEnlaceRecuperacionContrasena(token);
    }*/
}