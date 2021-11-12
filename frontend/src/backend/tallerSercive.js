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

export const buscarTrabajos = (onSuccess) => {

    let path = `/taller/trabajo`;

    appFetch(path, config('GET'), onSuccess);
}

export const buscarReparaciones = (idTrabajo, onSuccess) => {

    let path = `/taller/trabajo/${idTrabajo}/reparaciones`;

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

export const crearTrabajo = (trabajoDto, onSuccess, onErrors) => {

    let path = `/taller/trabajo`;

    appFetch(path, config('POST', trabajoDto), onSuccess, onErrors);
}