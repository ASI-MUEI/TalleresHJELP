import {FormattedDate, FormattedMessage, useIntl} from "react-intl";
import {Link} from "react-router-dom";
import {Badge} from "react-bootstrap";
import commonFunctions from "../../commons/functions";

const Trabajos = ({listaTrabajos}) =>{

    const intl = useIntl();

    return(
        <table className="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='trabajo.matricula' />
                </th>
                <th scope="col">
                    <FormattedMessage id='trabajo.cliente' />
                </th>
                <th scope="col">
                    <FormattedMessage id='trabajo.estado' />
                </th>
                <th scope="col">
                    <FormattedMessage id='trabajo.fechaCreacion' />
                </th>
            </tr>
            </thead>

            <tbody>
            {listaTrabajos.map(trabajo =>
                <tr key={trabajo.matricula}>
                    <td>
                        <Link to={`trabajos/${trabajo.idTrabajo}`}>{trabajo.matricula}</Link>
                    </td>
                    <td>
                        {trabajo.nombreCliente}
                    </td>
                    <td className="align-middle">
                        <Badge variant={commonFunctions.tipoLabelTrabajo(trabajo.estado)}>
                            {intl.formatMessage({ id: 'trabajo.estado.' + trabajo.estado })}
                        </Badge>
                    </td>
                    <td className="align-middle">
                        <FormattedDate value={new Date(trabajo.fecha)}/>
                    </td>
                </tr>
            )}
            </tbody>

        </table>
    )
}

export default Trabajos;