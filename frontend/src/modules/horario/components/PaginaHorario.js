import { Container } from "react-bootstrap";
import NuevaReparacion from "../../reparacion/components/NuevaReparacion";
import Horario from "./Horario";
import { useState, useEffect } from "react";
import { Spinner } from "react-bootstrap";
import backend from "../../../backend";

const PaginaHorario = () => {
    var fechaHoy = new Date();
    const [reparaciones, setReparaciones] = useState(null);

    const getFechaFormateada = (fecha) => {

        var mes = fecha.getMonth()+1; //obteniendo mes
        var dia = fecha.getDate(); //obteniendo dia
        var ano = fecha.getFullYear(); //obteniendo año
        if(dia<10)
            dia='0'+dia; //agrega cero si el menor de 10
        if(mes<10)
            mes='0'+mes //agrega cero si el menor de 10

        return `${ano}-${mes}-${dia}`;
    }

    const [fechaReparaciones, setFechaReparaciones] = useState(getFechaFormateada(fechaHoy));


    useEffect(() => {
        setReparaciones(null)
        backend.tallerService.buscarAsistenciasPorFecha(
            fechaReparaciones,
            resultado => setReparaciones(resultado)
        )
    }, [fechaReparaciones])

    return (
        <Container>
            <div>
                <input
                    type="date"
                    value={fechaReparaciones}
                    onChange={event =>
                        setFechaReparaciones(event.target.value)} />
            </div>
            <br />

            {
                reparaciones === null ?
                    <div className="center">
                        <Spinner animation="border" role="status">
                            <span className="visually-hidden" />
                        </Spinner>
                    </div>

                    :
                    <div>
                        <Horario reparaciones={reparaciones} />
                    </div>
            }
            <br />
            <hr />
            <NuevaReparacion />
        </Container>
    )
}

export default PaginaHorario;