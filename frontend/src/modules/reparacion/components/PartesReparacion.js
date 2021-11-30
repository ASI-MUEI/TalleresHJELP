import {FormattedMessage} from "react-intl";
import {Link} from "react-router-dom";
import backend from "../../../backend"


const PartesReparacion = ({listaPartes}) => {


    const eliminarParteReparacion = (idAsistencia, idPieza) => {
        backend.tallerService.eliminarPiezaAsistencia(
            {
                idAsistencia: idAsistencia,
                idPieza: idPieza
            }
        )
        window.location.reload()
    }

    return (
        <table className="table table-striped table-hover" >
            <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='piezaReparacion.idPiezaReparacion'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='piezaReparacion.nombre'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='piezaReparacion.numeroUnidades'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='piezaReparacion.manual'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='reparacion.precio'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='piezaReparacion.eliminar'/>
                </th>
            </tr>
            </thead>

            <tbody>
            {listaPartes.map(pieza =>
                <tr key={pieza.idPieza}>
                    <td>
                        {pieza.idPieza}
                    </td>
                    <td>
                        {pieza.nombre}
                    </td>
                    <td>
                        {pieza.numeroUnidades}
                    </td>
                    <td>
                        <a href={pieza.manual}>Ver manual</a>
                    </td>
                    <td>
                        {pieza.precio}€
                    </td>
                    <td>
                        <Link onClick={event => eliminarParteReparacion(pieza.idAsistencia, pieza.idPieza)}> Eliminar </Link>
                    </td>
                </tr>
            )}
            </tbody>

        </table>
    )
}

export default PartesReparacion;