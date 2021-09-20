package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {

    private static final Lenguaje LENGUAJE_POR_DEFECTO = Lenguaje.es;

    private Long idUsuario;
    @Size(min = 1, max = 50)
    private String nombreUsuario;
    @Size(max = 50)
    private String nombrePilaUsuario;
    @Size(max = 100)
    private String apellidosUsuario;
    @Size(max = 90)
    @Email
    private String correoElectronicoUsuario;
    @Size(max = 100)
    private String contrasenaUsuario;
    private boolean cuentaEliminada;
    private Lenguaje lenguaje;
    private RolUsuarioSistema rolUsuarioSistema;

    public Usuario() {
        rolUsuarioSistema = RolUsuarioSistema.ESTANDAR;
        cuentaEliminada = false;
        lenguaje = LENGUAJE_POR_DEFECTO;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombrePilaUsuario() {
        return nombrePilaUsuario;
    }

    public void setNombrePilaUsuario(String nombrePilaUsuario) {
        this.nombrePilaUsuario = nombrePilaUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

    public String getCorreoElectronicoUsuario() {
        return correoElectronicoUsuario;
    }

    public void setCorreoElectronicoUsuario(String correoElectronicoUsuario) {
        this.correoElectronicoUsuario = correoElectronicoUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public boolean isCuentaEliminada() {
        return cuentaEliminada;
    }

    public void setCuentaEliminada(boolean cuentaEliminada) {
        this.cuentaEliminada = cuentaEliminada;
    }

    public Lenguaje getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(Lenguaje lenguaje) {
        this.lenguaje = lenguaje;
    }

    public RolUsuarioSistema getRolUsuarioSistema() {
        return rolUsuarioSistema;
    }

    public void setRolUsuarioSistema(RolUsuarioSistema rolUsuarioSistema) {
        this.rolUsuarioSistema = rolUsuarioSistema;
    }
}