package com.hjelp.backend.model.services;

import com.hjelp.backend.model.daos.UsuarioDao;
import com.hjelp.backend.model.entities.Lenguaje;
import com.hjelp.backend.model.entities.RolUsuarioSistema;
import com.hjelp.backend.model.entities.Usuario;
import com.hjelp.backend.model.exceptions.CampoDuplicadoException;
import com.hjelp.backend.model.exceptions.IncorrectLoginException;
import com.hjelp.backend.model.exceptions.IncorrectPasswordException;
import com.hjelp.backend.model.exceptions.InstanceNotFoundException;
import com.hjelp.backend.rest.common.JwtGenerator;
import com.hjelp.backend.rest.dtos.UsuarioCambioContraseñaDto;
import com.hjelp.backend.rest.dtos.UsuarioDto;
import com.hjelp.backend.rest.dtos.UsuarioLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Slice;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioUsuarioImpl implements ServicioUsuario {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    MessageSource messageSource;

    @Autowired
    JwtGenerator jwtGenerator;

    @Override
    public Usuario recuperarUsuario(String nombreUsuario) throws InstanceNotFoundException {
        Optional<Usuario> u = usuarioDao.findByNombreUsuario(nombreUsuario);

        if (!u.isPresent()) {
            throw new InstanceNotFoundException(Usuario.class.getName(), nombreUsuario);
        }

        return u.get();
    }

    /**
     * Crea un nuevo usuario con los datos del formulario de registro.
     *
     * @param usuarioDto Datos del formulario de registro.
     * @throws CampoDuplicadoException Si el nombre de usuario o el correo electrónico ya existen en la aplicaciçon.
     */
    @Override
    public void registrarUsuario(UsuarioDto usuarioDto) throws CampoDuplicadoException{


        Optional<Usuario> usuarioOptionalNombreUsuario = usuarioDao.findByNombreUsuario(usuarioDto.getNombreUsuario());
        Optional<Usuario> usuarioOptionalCorreoElectronico = usuarioDao.findByCorreoElectronicoUsuario(usuarioDto.getNombreUsuario());
        // Se valida que el nombre de usuario sea único
        if(usuarioOptionalNombreUsuario.isPresent()){
            throw new CampoDuplicadoException("entidades.usuario.nombreusuario", usuarioDto.getNombreUsuario());
        }

        Usuario usuario = new Usuario();

        usuario.setNombreUsuario(usuarioDto.getNombreUsuario());
        if(usuarioDto.getRolUsuario().equals("0")){
            usuario.setContrasenaUsuario(passwordEncoder.encode(usuarioDto.getContraseña()));
        }
        usuario.setNombrePilaUsuario(usuarioDto.getNombrePilaUsuario());
        usuario.setApellidosUsuario(usuarioDto.getApellidosUsuario());
        usuario.setCorreoElectronicoUsuario(usuarioDto.getEmail());
        usuario.setLenguaje(Lenguaje.values()[usuarioDto.getLenguaje()]);
        usuario.setRolUsuarioSistema(RolUsuarioSistema.values()[Integer.valueOf(usuarioDto.getRolUsuario())]);
        usuario.setDni(usuarioDto.getDni());

        usuarioDao.save(usuario);
    }

    /**
     * Función que permite a un usuario iniciar sesión
     *
     * @param usuarioLoginDto Datos del formulario de inicio de sesión
     * @return Los datos del usuario
     * @throws IncorrectLoginException Si el usuario no existe o existe pero su cuenta ha sido borrada
     *
     */
    @Override
    public Usuario iniciarSesionUsuario(UsuarioLoginDto usuarioLoginDto) throws IncorrectLoginException {

        Optional<Usuario> usuarioOptional = usuarioDao.findByNombreUsuario(usuarioLoginDto.getNombreUsuario());

        if (!usuarioOptional.isPresent() || (usuarioOptional.isPresent()) && usuarioOptional.get().isCuentaEliminada()){
            throw new IncorrectLoginException();
        }

        if (!passwordEncoder.matches(usuarioLoginDto.getContraseñaUsuario(),
                usuarioOptional.get().getContrasenaUsuario())) {
            throw new IncorrectLoginException();
        }

        return usuarioOptional.get();
    }


    @Override
    public void cambiarContrasenaUsuario(UsuarioCambioContraseñaDto usuarioCambioContraseñaDto, boolean isFromReset)
            throws IncorrectPasswordException {

        Optional<Usuario> usuarioOptional =
                usuarioDao.findByNombreUsuario(usuarioCambioContraseñaDto.getNombreUsuario());



        // Esto no debería ser posible
        if (!usuarioOptional.isPresent()) {
            return;
        }

        Usuario usuario = usuarioOptional.get();

        // Este método se reutiliza para el cambio normal de contraseña como para restablecerla luego de olvidarla.
        if(!isFromReset){
            if (!passwordEncoder.matches(usuarioCambioContraseñaDto.getContraseñaAntigua(),
                    usuario.getContrasenaUsuario())) {
                throw new IncorrectPasswordException();
            }
        }

        usuario.setContrasenaUsuario(passwordEncoder.encode(usuarioCambioContraseñaDto.getContraseñaNueva()));

        usuarioDao.save(usuario);
    }

    @Override
    public Usuario actualizarDatosUsuario(UsuarioDto datosFormularioActualizacion) throws InstanceNotFoundException {

        Optional<Usuario> usuarioOptional = usuarioDao.findByNombreUsuario(datosFormularioActualizacion.getNombreUsuario());

        if (!usuarioOptional.isPresent()) {
            throw new InstanceNotFoundException("usuario.idUsuario", datosFormularioActualizacion.getIdUsuario());
        }
        Usuario usuario = usuarioOptional.get();

        // Incorporación de datos del formulario
        usuario.setNombrePilaUsuario(datosFormularioActualizacion.getNombrePilaUsuario());
        usuario.setApellidosUsuario(datosFormularioActualizacion.getApellidosUsuario());
        usuario.setCorreoElectronicoUsuario(datosFormularioActualizacion.getEmail());

        usuarioDao.save(usuario);
        return usuario;
    }

    @Override
    public Slice<Usuario> findMecanicos() {
        return usuarioDao.findByRolUsuario(RolUsuarioSistema.MECANICO);
    }
}
