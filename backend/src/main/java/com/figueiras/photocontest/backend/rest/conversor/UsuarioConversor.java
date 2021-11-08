package com.figueiras.photocontest.backend.rest.conversor;

import com.figueiras.photocontest.backend.model.entities.Usuario;
import com.figueiras.photocontest.backend.rest.dtos.MecanicoDto;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioAutenticadoDto;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioDto;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioTablaDto;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.List;

public class UsuarioConversor {


    public static UsuarioTablaDto toUsuarioTablaDto(Usuario usuario) {

        UsuarioTablaDto usuarioTablaDto = new UsuarioTablaDto();

        usuarioTablaDto.setIdUsuario(usuario.getIdUsuario());
        usuarioTablaDto.setNombreUsuario(usuario.getNombreUsuario());
        usuarioTablaDto.setNombrePilaUsuario(usuario.getNombrePilaUsuario());
        usuarioTablaDto.setApellidosUsuario(usuario.getApellidosUsuario());
        usuarioTablaDto.setCuentaEliminada(usuario.isCuentaEliminada());

        return usuarioTablaDto;
    }

    public static List<UsuarioTablaDto> toUsuariosTablaDto(List<Usuario> usuarios) {

        List<UsuarioTablaDto> usuariosTablaDto = new ArrayList<>();

        for (Usuario u : usuarios) {

            usuariosTablaDto.add(toUsuarioTablaDto(u));
        }
        return usuariosTablaDto;
    }

    public static UsuarioDto toUsuarioDto(Usuario usuario) {

        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setNombreUsuario(usuario.getNombreUsuario());
        usuarioDto.setEmail(usuario.getCorreoElectronicoUsuario());
        usuarioDto.setNombrePilaUsuario(usuario.getNombrePilaUsuario());
        usuarioDto.setApellidosUsuario(usuario.getApellidosUsuario());
        usuarioDto.setCuentaEliminada(usuario.isCuentaEliminada());
        usuarioDto.setLenguaje(usuario.getLenguaje().ordinal());
        usuarioDto.setRolUsuario(usuario.getRolUsuarioSistema().toString());
        return usuarioDto;
    }

    public static List<UsuarioDto> toUsuariosDto(
            List<Usuario> usuarioList) {

        List<UsuarioDto> usuariosDto = new ArrayList<>();

        for (Usuario usuario : usuarioList) {
            usuariosDto.add(toUsuarioDto(usuario));
        }

        return usuariosDto;
    }

    public static UsuarioAutenticadoDto toUsuarioAutenticadoDto(Usuario usuario, String token) {

        UsuarioAutenticadoDto usuarioAutenticadoDto = new UsuarioAutenticadoDto();

        usuarioAutenticadoDto.setUsuarioDto(toUsuarioDto(usuario));
        usuarioAutenticadoDto.setTokenJwt(token);

        return usuarioAutenticadoDto;
    }

    public static List<MecanicoDto> toMecanicoDto(Slice<Usuario> usuarios){
        List <MecanicoDto> mecanicos = new ArrayList<>();
        for (Usuario mecanico : usuarios){
            mecanicos.add(new MecanicoDto(mecanico.getIdUsuario(), mecanico.getNombreUsuario()));
        }
        return mecanicos;
    }
}
