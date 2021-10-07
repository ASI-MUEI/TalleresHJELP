-- noinspection SqlNoDataSourceInspectionForFile
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

CREATE TABLE Modelo(
   idModelo BIGINT NOT NULL AUTO_INCREMENT,
   nombre VARCHAR(255),
   descripcion VARCHAR(255),
   idMarca BIGINT,
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
    idMarca BIGINT,
    idModelo BIGINT,
    idFlota BIGINT,
    CONSTRAINT Vehiculo_pk PRIMARY KEY(idVehiculo),
    CONSTRAINT usuario_fk FOREIGN KEY(idUsuario) REFERENCES Usuario(idUsuario),
    CONSTRAINT marca_fk FOREIGN KEY(idMarca) REFERENCES Marca(idMarca),
    CONSTRAINT modelo_fk FOREIGN KEY(idModelo) REFERENCES Modelo(idModelo),
    CONSTRAINT flota_fk FOREIGN KEY(idFlota) REFERENCES Flota(idFlota),
    CONSTRAINT matricula_unique UNIQUE(matricula),
    CONSTRAINT numBastidor_unique UNIQUE(numBastidor)
);

CREATE TABLE Documento(
    idDocumento BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255),
    descripcion VARCHAR(255),
    localizacion VARCHAR(255),
    CONSTRAINT Documento_pk PRIMARY KEY(idDocumento)
);

CREATE TABLE PuestoTaller(
    idPuesto BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255),
    descripcion VARCHAR(255),
    CONSTRAINT PuestoTaller_pk PRIMARY KEY(idPuesto)
);

CREATE TABLE PuestoTallerVehiculo(
  idPuestoVehiculo BIGINT NOT NULL AUTO_INCREMENT,
  idPuesto BIGINT NOT NULL,
  idVehiculo BIGINT NOT NULL,
  fechaComienzo DATE,
  fechaFinal DATE,
  CONSTRAINT PuestoTallerVehiculo_pk PRIMARY KEY(idPuestoVehiculo),
  CONSTRAINT vehiculo_fk_puestoTallerVehiculo FOREIGN KEY(idVechiculo) REFERENCES Vehiculo(idVehiculo),
  CONSTRAINT puesto_fk_puestoTallerVehiculo FOREIGN KEY(idPuesto) REFERENCES PuestoTaller(idPuesto)

);

