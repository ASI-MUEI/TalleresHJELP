-- noinspection SqlNoDataSourceInspectionForFile
DROP TABLE AsistenciaPieza;
DROP TABLE Pieza;
DROP TABLE AsistenciaMecanico;
DROP TABLE AsistenciaHorario;
DROP TABLE Asistencia;
DROP TABLE TipoAsistencias;
DROP TABLE Horarios;
DROP TABLE Trabajo;
DROP TABLE EstadoTrabajo;
DROP TABLE Vehiculo;
DROP TABLE Flota;
DROP TABLE Modelo;
DROP TABLE Marca;
DROP TABLE Usuario;
DROP TABLE PuestoTaller;

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
    dni VARCHAR(50) UNIQUE,
    CONSTRAINT Usuario_pk PRIMARY KEY(idUsuario),
    CONSTRAINT Nombre_usuario_unique UNIQUE(nombreUsuario)
);

CREATE TABLE Marca(
  idMarca BIGINT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(255),
  descripcion VARCHAR(255),
  CONSTRAINT Marca_pk PRIMARY KEY(idMarca)
);

CREATE TABLE Modelo(
   idModelo BIGINT NOT NULL AUTO_INCREMENT,
   nombre VARCHAR(255),
   descripcion VARCHAR(255),
   idMarca BIGINT,
   manual VARCHAR(1000),
   CONSTRAINT Modelo_pk PRIMARY KEY(idModelo),
   CONSTRAINT Modelo_fk_marca FOREIGN KEY(idMarca) REFERENCES Marca(idMarca)
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

CREATE TABLE TipoAsistencias(
     idTipo BIGINT NOT NULL AUTO_INCREMENT,
     nombre VARCHAR(255),
     descripcion VARCHAR(255),
     CONSTRAINT TipoAsistencias_pk PRIMARY KEY(idTipo)
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
    peritado INT,
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
    retrasada TINYINT,
    motivoRetraso VARCHAR(255),
    CONSTRAINT asistencia_pk PRIMARY KEY(idAsistencia),
    CONSTRAINT tipoAsistencia_fk FOREIGN KEY(idTipo) REFERENCES TipoAsistencias(idTipo),
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
    idAsistenciaHorario BIGINT NOT NULL AUTO_INCREMENT,
    idAsistencia BIGINT NOT NULL,
    idHorario BIGINT NOT NULL,
    CONSTRAINT asistenciaHorario_pk PRIMARY KEY(idAsistenciaHorario),
    CONSTRAINT asistenciaHorario_idAsistencia_fk FOREIGN KEY(idAsistencia) REFERENCES Asistencia(idAsistencia),
    CONSTRAINT asistenciaHorario_idHorario_fk FOREIGN KEY(idHorario) REFERENCES Horarios(idFranjaHoraria)
);

CREATE TABLE Pieza(
    idPieza BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255),
    descripcion VARCHAR(255),
    manual VARCHAR(1000),
    precio DOUBLE,
    CONSTRAINT pieza_pk PRIMARY KEY(idPieza)
);

CREATE TABLE AsistenciaPieza(
    idAsistenciaPieza BIGINT NOT NULL AUTO_INCREMENT,
    idAsistencia BIGINT NOT NULL,
    idPieza BIGINT NOT NULL,
    numeroUnidades BIGINT,
    CONSTRAINT asistenciaPieza_pk PRIMARY KEY(idAsistenciaPieza),
    CONSTRAINT asistenciaPieza_idAsistencia_fk FOREIGN KEY(idAsistencia) REFERENCES Asistencia(idAsistencia),
    CONSTRAINT asistenciaPieza_idPieza_fk FOREIGN KEY(idPieza) REFERENCES Pieza(idPieza)
);