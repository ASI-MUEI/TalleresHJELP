import {FormattedDate, FormattedMessage, useIntl} from "react-intl";
import {Link} from "react-router-dom";
import {Badge} from "react-bootstrap";
import commonFunctions from "../../commons/functions";

const Reparaciones = ({listaReparaciones}) =>{

    const intl = useIntl();

    return(
        <table className="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='reparacion.fecha' />
                </th>
                <th scope="col">
                    <FormattedMessage id='reparacion.duracion' />
                </th>
                <th scope="col">
                    <FormattedMessage id='reparacion.precio' />
                </th>
                <th scope="col">
                    <FormattedMessage id='reparacion.detalle' />
                </th>
            </tr>
            </thead>

            <tbody>
            {listaReparaciones.map(reparacion =>
                <tr key={reparacion.id}>
                    <td>
                        <FormattedDate value={new Date(reparacion.fechaCreacion)} />
                    </td>
                    <td>
                        {reparacion.duracion}
                    </td>
                    <td>
                        {reparacion.elevador}
                    </td>
                    <td>
                        {reparacion.precio}€
                    </td>
                    <td>
                        <Link
                            to={`reparaciones/${reparacion.id}`}>
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