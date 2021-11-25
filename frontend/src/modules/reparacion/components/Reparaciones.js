import {FormattedDate, FormattedMessage} from "react-intl";
import {Link} from "react-router-dom";

const Reparaciones = ({listaReparaciones}) => {

    return (
        <table className="table table-striped table-hover" >
            <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='reparacion.fecha'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='reparacion.duracion'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='reparacion.puesto'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='reparacion.precio'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='reparacion.detalle'/>
                </th>
            </tr>
            </thead>

            <tbody>
            {listaReparaciones.map(reparacion =>
                <tr key={reparacion.id}>
                    <td>
                        <FormattedDate value={new Date(reparacion.fecha)}/>
                    </td>
                    <td>
                        {reparacion.duracionEstimada} horas
                    </td>
                    <td>
                        {reparacion.nombreElevador}
                    </td>
                    <td>
                        {reparacion.precio}€
                    </td>
                    <td>
                        <Link
                            to={`/reparaciones/${reparacion.idReparacion}`}>
                            <FormattedMessage id={"reparacion.verDetalle"}/>
                        </Link>
                    </td>
                </tr>
            )}
            </tbody>

        </table>
    )
}

export default Reparaciones;