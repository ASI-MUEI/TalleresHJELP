-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE Usuario;

CREATE TABLE Usuario(
    idUsuario BIGINT NOT NULL AUTO_INCREMENT,
    nombreUsuario VARCHAR(50),
    nombrePilaUsuario VARCHAR(50),
    apellidosUsuario VARCHAR(100),
    correoElectronicoUsuario VARCHAR(90),
    contrasenaUsuario VARCHAR(100),
    lenguaje int,
    rolUsuarioSistema int,
    cuentaEliminada TINYINT,
    CONSTRAINT Usuario_pk PRIMARY KEY(idUsuario),
    CONSTRAINT Nombre_usuario_unique UNIQUE(nombreUsuario)
);