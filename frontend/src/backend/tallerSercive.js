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