import { config, appFetch } from './appFetch';

export const buscarAsistenciasPorFecha = (fecha, onSuccess) => {

    let path = '/taller/asistencias/';

    path += fecha ? fecha : "";

    appFetch(path, config('GET'), onSuccess);
}