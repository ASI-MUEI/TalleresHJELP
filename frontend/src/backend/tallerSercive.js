import { config, appFetch } from './appFetch';

export const buscarAsistenciasPorFecha = (fecha, onSuccess) => {

    let path = '/taller/asistencias/';

    path += fecha ? fecha : "";

    appFetch(path, config('GET'), onSuccess);
}

export const buscarTrabajosActivos = (onSuccess) => {

    let path = `/taller/trabajo/activos`;

    appFetch(path, config('GET'), onSuccess);
}

export const buscarReparacionesAtrasadas = (onSuccess) => {

    let path = `/taller/asistencias/retrasadas`;

    appFetch(path, config('GET'), onSuccess);
}

export const buscarElevadores = (onSuccess) => {

    let path = `/taller/elevadores`;

    appFetch(path, config('GET'), onSuccess);
}

export const buscarHorarios = (onSuccess) => {

    let path = `/taller/asistencias/horarios`;

    appFetch(path, config('GET'), onSuccess);
}

export const crearReparacion = (asistenciaDto, onSuccess, onErrors) => {

    let path = `/taller/asistencia`;

    appFetch(path, config('POST', asistenciaDto), onSuccess, onErrors);
}

export const buscarTrabajos = (page, size, onSuccess) => {

    let path = `/taller/trabajo?page=${page}&size=${size}`;

    appFetch(path, config('GET'), onSuccess);
}

export const buscarReparaciones = (idTrabajo, page ,size ,onSuccess) => {

    let path = `/taller/trabajo/${idTrabajo}/reparaciones?page=${page}&size=${size}`;

    appFetch(path, config('GET'), onSuccess);
}

export const buscarTrabajoPorId = (id, onSuccess) => {

    let path = `/taller/trabajo/${id}`;

    appFetch(path, config('GET'), onSuccess);
}

export const buscarReparacionPorId = (id, onSuccess) => {

    let path = `/taller/reparacion/${id}`;

    appFetch(path, config('GET'), onSuccess);
}

export const recuperarMatriculas = (onSuccess) => {

    let path = '/catalogo-vehiculos/matriculas';

    appFetch(path, config('GET'), onSuccess);
}

export const buscarPiezasReparacion = (idAsistencia, page, size, onSuccess) => {

    let path = `/taller/asistencias/${idAsistencia}/piezas?page=${page}&size=${size}`;

    appFetch(path, config('GET'), onSuccess);
}

//TODO: buscar lista de tipos de reparaciones

export const crearTrabajo = (trabajoDto, onSuccess, onErrors) => {

    let path = `/taller/trabajo`;

    appFetch(path, config('POST', trabajoDto), onSuccess, onErrors);
}

export const getAllPiezas = (onSuccess) => {

    let path = `/taller/piezas`;

    appFetch(path, config('GET'), onSuccess);
}

export const asignarPiezaAsistencia = (nuevaAsistenciaPiezaDto, onSuccess) => {

    let path = `/taller/asistencias/updatePieza`;

    appFetch(path, config('PUT', nuevaAsistenciaPiezaDto), onSuccess);
}

export const eliminarPiezaAsistencia = (eliminarAsistenciaPiezaDto, onSuccess) => {

    let path = `/taller/asistencias/removePieza`;

    appFetch(path, config('PUT', eliminarAsistenciaPiezaDto), onSuccess);
}

export const cambiarRetraso = (idAsistencia, motivo, onSuccess) => {

    let path = `/taller/asistencia/${idAsistencia}/update/retraso`;

    appFetch(path, config('PUT', motivo), onSuccess);
}

export const getFactura = (idTrabajo, onSuccess) => {

    let path = `/taller/factura/${idTrabajo}`;

    appFetch(path, config('GET'), onSuccess);
}

export const cambiarEstadoTrabajo = (idTrabajo, estado, onSuccess) => {

    let path = `/taller/trabajo/${idTrabajo}/estado`;

    appFetch(path, config('PUT', estado), onSuccess);
}

export const getTiposTarea = (onSuccess) => {

    let path = `/taller/tiposTarea`;

    appFetch(path, config('GET'), onSuccess);
}