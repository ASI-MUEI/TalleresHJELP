import {FormattedDate, FormattedMessage} from "react-intl";
import {Link} from "react-router-dom";

const ReparacionesAtrasadas = ({listaReparacionesAtrasadas}) => {

    return (
        <table className="table table-striped table-hover" >
            <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='reparacion.idAsistencia'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='reparacion.atrasada.motivo'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='reparacion.atrasada.actualizar'/>
                </th>
            </tr>
            </thead>

            <tbody>
            {listaReparacionesAtrasadas.map(reparacion =>
                <tr key={reparacion.id}>
                    <td>
                        <FormattedDate value={new Date(reparacion.fecha)}/>
                    </td>
                    <td>
                        {reparacion.idAsistencia} horas
                    </td>
                    <td>
                        {reparacion.motivoRetraso}
                    </td>
                    <td>
                        <Link
                            to={`/reparaciones/${reparacion.idAsistencia}/actualizar`}>
                            <FormattedMessage id={"reparacion.retrasada.actualizar"}/>
                        </Link>
                    </td>
                </tr>
            )}
            </tbody>

        </table>
    )
}

export default ReparacionesAtrasadas;