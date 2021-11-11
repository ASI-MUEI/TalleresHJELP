-- noinspection SqlNoDataSourceInspectionForFile
DROP TABLE AsistenciaMecanico;
DROP TABLE AsistenciaHorario;
DROP TABLE Asistencia;
DROP TABLE TiposAsistencias;
DROP TABLE Horarios;
DROP TABLE Trabajo;
DROP TABLE EstadoTrabajo;
DROP TABLE Vehiculo;
DROP TABLE Flota;
DROP TABLE Modelo;
DROP TABLE Marca;
DROP TABLE Usuario;
DROP TABLE Documento;
DROP TABLE PuestoTaller;

CREATE TABLE Usuario(
    idUsuario BIGINT NOT NULL AUTO_INCREMENT,
    nombreUsuario VARCHAR(50),
    nombrePilaUsuario VARCHAR(50),
    apellidosUsuario VARCHAR(100),
    correoElectronicoUsuario VARCHAR(90) UNIQUE,
    contrasenaUsuario VARCHAR(100),
    lenguaje int,
    rolUsuarioSistema int,
    cuentaEliminada TINYINT,
    CONSTRAINT Usuario_pk PRIMARY KEY(idUsuario),
    CONSTRAINT Nombre_usuario_unique UNIQUE(nombreUsuario)
);

CREATE TABLE Marca(
  idMarca BIGINT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(255),
  descripcion VARCHAR(255),
  CONSTRAINT Marca_pk PRIMARY KEY(idMarca)
);

CREATE TABLE Documento(
  idDocumento BIGINT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(255),
  descripcion VARCHAR(255),
  localizacion VARCHAR(255),
  CONSTRAINT Documento_pk PRIMARY KEY(idDocumento)
);

CREATE TABLE Modelo(
   idModelo BIGINT NOT NULL AUTO_INCREMENT,
   nombre VARCHAR(255),
   descripcion VARCHAR(255),
   idMarca BIGINT,
   idDocumento BIGINT,
   CONSTRAINT Modelo_pk PRIMARY KEY(idModelo),
   CONSTRAINT Modelo_fk_marca FOREIGN KEY(idMarca) REFERENCES Marca(idMarca),
   CONSTRAINT Modelo_fk_doc FOREIGN KEY(idDocumento) REFERENCES Documento(idDocumento)
);

CREATE TABLE Flota(
  idFlota BIGINT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(255),
  descripcion VARCHAR(255),
  CONSTRAINT Modelo_pk PRIMARY KEY(idFlota)
);

CREATE TABLE Vehiculo(
    idVehiculo BIGINT NOT NULL AUTO_INCREMENT,
    idUsuario BIGINT NOT NULL,
    numBastidor VARCHAR(255) NOT NULL,
    matricula VARCHAR(255) NOT NULL,
    idModelo BIGINT,
    idFlota BIGINT,
    CONSTRAINT Vehiculo_pk PRIMARY KEY(idVehiculo),
    CONSTRAINT usuario_fk FOREIGN KEY(idUsuario) REFERENCES Usuario(idUsuario),
    CONSTRAINT modelo_fk FOREIGN KEY(idModelo) REFERENCES Modelo(idModelo),
    CONSTRAINT flota_fk FOREIGN KEY(idFlota) REFERENCES Flota(idFlota),
    CONSTRAINT matricula_unique UNIQUE(matricula),
    CONSTRAINT numBastidor_unique UNIQUE(numBastidor)
);

CREATE TABLE PuestoTaller(
    idPuesto BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255),
    descripcion VARCHAR(255),
    CONSTRAINT PuestoTaller_pk PRIMARY KEY(idPuesto)
);

CREATE TABLE Horarios(
    idFranjaHoraria BIGINT NOT NULL AUTO_INCREMENT,
    franjaHoraria VARCHAR(255),
    CONSTRAINT pk_Horarios PRIMARY KEY(idFranjaHoraria)
);

CREATE TABLE TiposAsistencias(
     idTipo BIGINT NOT NULL AUTO_INCREMENT,
     nombre VARCHAR(255),
     descripcion VARCHAR(255),
     CONSTRAINT TiposAsistencias_pk PRIMARY KEY(idTipo)
);

CREATE TABLE EstadoTrabajo(
   idEstado BIGINT NOT NULL AUTO_INCREMENT,
   nombre VARCHAR(255),
   descripcion VARCHAR(255),
   CONSTRAINT EstadosTrabajo_pk PRIMARY KEY(idEstado)
);

CREATE TABLE Trabajo(
	idTrabajo BIGINT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    idVehiculo BIGINT NOT NULL,
    idEstado BIGINT(255),
    fechaCreado DATETIME,
    CONSTRAINT Trabajo_pk PRIMARY KEY(idTrabajo),
    CONSTRAINT idEstado_fk FOREIGN KEY(idEstado) REFERENCES EstadoTrabajo(idEstado),
    CONSTRAINT idVehiculo_fk FOREIGN KEY(idVehiculo) REFERENCES Vehiculo(idVehiculo)
);

CREATE TABLE Asistencia(
    idAsistencia BIGINT NOT NULL AUTO_INCREMENT,
    idTipo BIGINT NOT NULL,
    fecha DATETIME,
    idPuesto BIGINT,
    idTrabajo BIGINT,
    precio FLOAT,
    duracionEstimada BIGINT,
    peritaje INT,
    descripcion VARCHAR(500),
    CONSTRAINT asistencia_pk PRIMARY KEY(idAsistencia),
    CONSTRAINT tipoAsistencia_fk FOREIGN KEY(idTipo) REFERENCES TiposAsistencias(idTipo),
    CONSTRAINT trabajo_fk FOREIGN KEY(idTrabajo) REFERENCES Trabajo(idTrabajo),
    CONSTRAINT idPuesto_fk FOREIGN KEY(idPuesto) REFERENCES PuestoTaller(idPuesto)
);

CREATE TABLE AsistenciaMecanico(
    idAsistencia BIGINT NOT NULL,
    idMecanico BIGINT NOT NULL,
    CONSTRAINT asistenciamecanico_pk PRIMARY KEY(idAsistencia, idMecanico),
    CONSTRAINT asistenciamecanico_idAsistencia_fk FOREIGN KEY(idAsistencia) REFERENCES Asistencia(idAsistencia),
    CONSTRAINT asistenciamecanico_idMecanico_fk FOREIGN KEY(idMecanico) REFERENCES Usuario(idUsuario)
);

CREATE TABLE AsistenciaHorario(
    idAsistencia BIGINT NOT NULL,
    idHorario BIGINT NOT NULL,
    CONSTRAINT asistenciaHorario_pk PRIMARY KEY(idAsistencia, idHorario),
    CONSTRAINT asistenciaHorario_idAsistencia_fk FOREIGN KEY(idAsistencia) REFERENCES Asistencia(idAsistencia),
    CONSTRAINT asistenciaHorario_idHorario_fk FOREIGN KEY(idHorario) REFERENCES Horarios(idFranjaHoraria)
);