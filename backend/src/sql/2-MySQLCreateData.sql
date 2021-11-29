INSERT INTO Usuario
(idUsuario, nombreUsuario, nombrePilaUsuario, apellidosUsuario, correoElectronicoUsuario, contrasenaUsuario, lenguaje, rolUsuarioSistema, cuentaEliminada)
VALUES
(1, "Maximiliano", "maximiliano", "Lucrecio", "maxluc@hjelp.es", "$2a$10$MOTlWknMV4VImu6vTQ9gq.yNIY37MJW4uM1wvBMsf.jRO0oviGyeq", 1, 0, 0);

INSERT INTO Usuario
(idUsuario, nombreUsuario, nombrePilaUsuario, apellidosUsuario, correoElectronicoUsuario, contrasenaUsuario, lenguaje, rolUsuarioSistema, cuentaEliminada)
VALUES
(2, "Pepe", "pepe", "Gómez", "pgomez@hjelp.es", "$2a$10$MOTlWknMV4VImu6vTQ9gq.yNIY37MJW4uM1wvBMsf.jRO0oviGyeq", 0, 2, 0);

INSERT INTO Usuario
(idUsuario, nombreUsuario, nombrePilaUsuario, apellidosUsuario, correoElectronicoUsuario, contrasenaUsuario, lenguaje, rolUsuarioSistema, cuentaEliminada)
VALUES
(3, "Fernando", "fernadno", "Gómez", "fernadno@hjelp.es", "$2a$10$MOTlWknMV4VImu6vTQ9gq.yNIY37MJW4uM1wvBMsf.jRO0oviGyeq", 0, 1, 0);

INSERT INTO Usuario
(idUsuario, nombreUsuario, nombrePilaUsuario, apellidosUsuario, correoElectronicoUsuario, contrasenaUsuario, lenguaje, rolUsuarioSistema, cuentaEliminada)
VALUES
(4, "Ismael", "ismael", "Gómez", "ismael@hjelp.es", "$2a$10$MOTlWknMV4VImu6vTQ9gq.yNIY37MJW4uM1wvBMsf.jRO0oviGyeq", 0, 1, 0);

INSERT INTO Usuario
(idUsuario, nombreUsuario, nombrePilaUsuario, apellidosUsuario, correoElectronicoUsuario, contrasenaUsuario, lenguaje, rolUsuarioSistema, cuentaEliminada)
VALUES
(5, "Laura", "laura", "Gómez", "lgomez@hjelp.es", "$2a$10$MOTlWknMV4VImu6vTQ9gq.yNIY37MJW4uM1wvBMsf.jRO0oviGyeq", 0, 2, 0);


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

INSERT INTO TipoAsistencias
(idTipo, nombre, descripcion)
VALUES
(1, 'Neumáticos', 'Se cambian los neumáticos del vehículo');

INSERT INTO TipoAsistencias
(idTipo, nombre, descripcion)
VALUES
(2, 'Aceite', 'Se cambian el aceite del vehículo');

INSERT INTO TipoAsistencias
(idTipo, nombre, descripcion)
VALUES
(3, 'Reparación', 'Reparación genérica');

-- Estados de asistencias

INSERT INTO EstadoTrabajo
(idEstado, nombre, descripcion)
VALUES
(1, 'Abierto', 'El vehículo está en reparación');

INSERT INTO EstadoTrabajo
(idEstado, nombre, descripcion)
VALUES
(2, 'Cerrado', 'El vehículo está listo');

INSERT INTO EstadoTrabajo
(idEstado, nombre, descripcion)
VALUES
(3, 'Atrasado', 'El vehículo está listo');

INSERT INTO EstadoTrabajo
(idEstado, nombre, descripcion)
VALUES
(4, 'Pagado', 'El vehículo está listo');


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

INSERT INTO Marca
(idMarca, nombre, descripcion)
VALUES
(2, 'Audi', 'Auto Unión');
-- Insercion de modelos

INSERT INTO Modelo
(idModelo, nombre, descripcion, idMarca, idDocumento)
VALUES
(1, 'Serie 1', 'Hatchback de la marca de Babaria', 1, NULL);

INSERT INTO Modelo
(idModelo, nombre, descripcion, idMarca, idDocumento)
VALUES
(2, 'A3', 'Hatchback de la marca Audi', 2, NULL);

INSERT INTO Vehiculo
(idVehiculo, idUsuario, numBastidor, matricula, idModelo, idFlota)
VALUES
(1, 2, 'VSSZZZ1MZ2R040807', '01234LLL', 1, NULL );

INSERT INTO Vehiculo
(idVehiculo, idUsuario, numBastidor, matricula, idModelo, idFlota)
VALUES
(2, 5, 'AUDZZZ1MZ2R040807', '91234KLM', 2, NULL );

-- INSERCION DE REPARACIONES

-- Inserción de trabajo
INSERT INTO Trabajo
(idTrabajo, nombre, descripcion, idVehiculo, idEstado, fechaCreado)
VALUES
(1, 'Reparación neumáticos', 'Se procede a cambiar los neumáticos de verano a invierno', 1, 1, CURRENT_DATE());

INSERT INTO Trabajo
(idTrabajo, nombre, descripcion, idVehiculo, idEstado, fechaCreado)
VALUES
(2, 'Reparación chapa', 'Se procede a reparar chapa tras accidente frontal', 2, 1, CURRENT_DATE());

-- Insercion de Asistencia

INSERT INTO Asistencia
(idAsistencia, idTipo, fecha, idPuesto, idTrabajo, precio, duracionEstimada, peritaje, descripcion)
VALUES
(1, 1, CURRENT_DATE(),  1, 1, 250, 2, 0, 'Se procede a cambiar los neumáticos de verano a invierno');

INSERT INTO Asistencia
(idAsistencia, idTipo, fecha, idPuesto, idTrabajo, precio, duracionEstimada, peritaje, descripcion)
VALUES
(2, 2, CURRENT_DATE(),  1, 1, 250, 2, 1, 'Se procede a cambiar los neumáticos de verano a invierno');

INSERT INTO Asistencia
(idAsistencia, idTipo, fecha, idPuesto, idTrabajo, precio, duracionEstimada, peritaje, descripcion)
VALUES
(3, 3, CURRENT_DATE(),  2, 2, 5500, 8, 0, 'Cambio de defensa y difusores frontales, espejo izquierdo');

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

INSERT INTO AsistenciaHorario
(idAsistencia, idHorario)
VALUES
(3, 1);

INSERT INTO AsistenciaHorario
(idAsistencia, idHorario)
VALUES
(3, 2);

INSERT INTO AsistenciaHorario
(idAsistencia, idHorario)
VALUES
(3, 3);


INSERT INTO AsistenciaHorario
(idAsistencia, idHorario)
VALUES
(3, 4);


INSERT INTO AsistenciaHorario
(idAsistencia, idHorario)
VALUES
(3, 5);


INSERT INTO AsistenciaHorario
(idAsistencia, idHorario)
VALUES
(3, 6);


INSERT INTO AsistenciaHorario
(idAsistencia, idHorario)
VALUES
(3, 7);


INSERT INTO AsistenciaHorario
(idAsistencia, idHorario)
VALUES
(3, 8);


INSERT INTO AsistenciaHorario
(idAsistencia, idHorario)
VALUES
(3, 9);


INSERT INTO AsistenciaHorario
(idAsistencia, idHorario)
VALUES
(3, 10);

-- Asignacion de mecanicos a asistencias
INSERT INTO AsistenciaMecanico
(idAsistencia, idMecanico)
VALUES
(1, 3);

INSERT INTO AsistenciaMecanico
(idAsistencia, idMecanico)
VALUES
(1, 4);

INSERT INTO AsistenciaMecanico
(idAsistencia, idMecanico)
VALUES
(2, 3);

INSERT INTO Pieza
(idPieza, nombre, descripcion)
VALUES
(1, 'Tubo de escape', 'Tubo de escape descripcion')