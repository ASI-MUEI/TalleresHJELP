import {FormattedDate, FormattedMessage} from "react-intl";
import {Link} from "react-router-dom";

const PartesReparacion = ({listaPartes}) => {

    const eliminarReparacion = (idPieza, idReparacion) => {
        //TODO: método que elimina una pieza de una reparacion
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
                    <FormattedMessage id='piezaReparacion.eliminar'/>
                </th>
            </tr>
            </thead>

            <tbody>
            {listaPartes.map(pieza =>
                <tr key={pieza.idPiezaReparacion}>
                    <td>
                        {pieza.idPiezaReparacion}
                    </td>
                    <td>
                        {pieza.nombre}
                    </td>
                    <td>
                        {pieza.numeroUnidades}
                    </td>
                    <td>
                        <a href={pieza.manual}/>
                    </td>
                    <td>
                        <Link onClick={eliminarReparacion(pieza.idPiezaReparacion, pieza.idReparacion)}> </Link>
                    </td>
                </tr>
            )}
            </tbody>

        </table>
    )
}

export default PartesReparacion;