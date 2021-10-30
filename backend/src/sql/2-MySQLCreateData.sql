INSERT INTO Usuario
(idUsuario, nombreUsuario, nombrePilaUsuario, apellidosUsuario, correoElectronicoUsuario, contrasenaUsuario, lenguaje, rolUsuarioSistema, cuentaEliminada)
VALUES
(1, "Rosemary", "rosemary", "Smith", "rosemarysmith@hjelp.es", "$2a$10$MOTlWknMV4VImu6vTQ9gq.yNIY37MJW4uM1wvBMsf.jRO0oviGyeq", 0, 0, 0);

INSERT INTO Usuario
(idUsuario, nombreUsuario, nombrePilaUsuario, apellidosUsuario, correoElectronicoUsuario, contrasenaUsuario, lenguaje, rolUsuarioSistema, cuentaEliminada)
VALUES
(2, "Pepe", "pepe", "Gómez", "pgomez@hjelp.es", "$2a$10$MOTlWknMV4VImu6vTQ9gq.yNIY37MJW4uM1wvBMsf.jRO0oviGyeq", 0, 2, 0);


INSERT INTO Horarios(franjaHoraria) values('8:30-9:00');
INSERT INTO Horarios(franjaHoraria) values('9:00-9:30');
INSERT INTO Horarios(franjaHoraria) values('9:30-10:00');
INSERT INTO Horarios(franjaHoraria) values('10:00-10:30');
INSERT INTO Horarios(franjaHoraria) values('10:30-11:00');
INSERT INTO Horarios(franjaHoraria) values('11:00-11:30');
INSERT INTO Horarios(franjaHoraria) values('11:30-12:00');
INSERT INTO Horarios(franjaHoraria) values('12:00-12:30');
INSERT INTO Horarios(franjaHoraria) values('12:30-13:00');
INSERT INTO Horarios(franjaHoraria) values('13:00-13:30');
-- Parada para comer
INSERT INTO Horarios(franjaHoraria) values('15:30-16:00');
INSERT INTO Horarios(franjaHoraria) values('16:00-16:30');
INSERT INTO Horarios(franjaHoraria) values('16:30-17:00');
INSERT INTO Horarios(franjaHoraria) values('17:00-17:30');
INSERT INTO Horarios(franjaHoraria) values('17:30-18:00');
INSERT INTO Horarios(franjaHoraria) values('18:00-18:30');
INSERT INTO Horarios(franjaHoraria) values('18:30-19:00');
INSERT INTO Horarios(franjaHoraria) values('19:00-19:30');
INSERT INTO Horarios(franjaHoraria) values('19:30-20:00');
INSERT INTO Horarios(franjaHoraria) values('20:00-20:30');

-- Tipos de asistencias

INSERT INTO TiposAsistencias
(idTipo, nombre, descripcion)
VALUES
(1, 'Cambio de neumáticos', 'Se cambian los neumáticos del vehículo');

INSERT INTO TiposAsistencias
(idTipo, nombre, descripcion)
VALUES
(2, 'Cambio de aceite', 'Se cambian el aceite del vehículo');

-- Estados de asistencias

INSERT INTO EstadosAsistencias
(idEstado, nombre, descripcion)
VALUES
(1, 'En Espera', 'El vehículo está esperando para su reparación');

INSERT INTO EstadosAsistencias
(idEstado, nombre, descripcion)
VALUES
(2, 'En reparación', 'El vehículo se está reparando');

INSERT INTO EstadosAsistencias
(idEstado, nombre, descripcion)
VALUES
(3, 'Listo', 'El vehículo está listo');

-- Insercion de puestos del taller
INSERT INTO PuestoTaller
(idPuesto, nombre, descripcion)
VALUES
(1, 'Elevador 1', 'Puesto de taller');

INSERT INTO PuestoTaller
(idPuesto, nombre, descripcion)
VALUES
(2, 'Elevador 2', 'Puesto de taller');

INSERT INTO PuestoTaller
(idPuesto, nombre, descripcion)
VALUES
(3, 'Elevador 3', 'Puesto de taller');

INSERT INTO PuestoTaller
(idPuesto, nombre, descripcion)
VALUES
(4, 'Elevador 4', 'Puesto de taller');

INSERT INTO PuestoTaller
(idPuesto, nombre, descripcion)
VALUES
(5, 'Elevador 5', 'Puesto de taller');


-- Insercion de Marcas

INSERT INTO Marca
(idMarca, nombre, descripcion)
VALUES
(1, 'BMW', 'Babarian Motors Works');
-- Insercion de modelos

INSERT INTO Modelo
(idModelo, nombre, descripcion, idMarca, idDocumento)
VALUES
(1, 'Serie 1', 'Hatchback de la marca de Babaria', 1, NULL);

INSERT INTO Vehiculo
(idVehiculo, idUsuario, numBastidor, matricula, idModelo, idFlota)
VALUES
(1, 2, 'VSSZZZ1MZ2R040807', '01234LLL', 1, NULL );

-- INSERCION DE REPARACIONES

-- Inserción de trabajo
INSERT INTO Trabajo
(idTrabajo, nombre, descripcion, idVehiculo)
VALUES
(1, 'Reparación neumáticos', 'Se procede a cambiar los neumáticos de verano a invierno', 1);

-- Insercion de Asistencia

INSERT INTO Asistencia
(idAsistencia, idTipo, fecha, idEstado, idPuesto, idTrabajo, precio, duracionEstimada, peritaje, descripcion)
VALUES
(1, 1, CURRENT_DATE(), 1,  1, 1, 250, 2, 0, 'Se procede a cambiar los neumáticos de verano a invierno');

INSERT INTO Asistencia
(idAsistencia, idTipo, fecha, idEstado, idPuesto, idTrabajo, precio, duracionEstimada, peritaje, descripcion)
VALUES
(2, 1, CURRENT_DATE(), 1,  1, 1, 250, 2, 1, 'Se procede a cambiar los neumáticos de verano a invierno');

-- Asignacion de horarios a asistencias

INSERT INTO AsistenciaHorario
(idAsistencia, idHorario)
VALUES
(1, 1);

INSERT INTO AsistenciaHorario
(idAsistencia, idHorario)
VALUES
(1, 2);

INSERT INTO AsistenciaHorario
(idAsistencia, idHorario)
VALUES
(2, 4);

-- Asignacion de mecanicos a asistencias
INSERT INTO AsistenciaMecanico
(idAsistencia, idMecanico)
VALUES
(1, 2);