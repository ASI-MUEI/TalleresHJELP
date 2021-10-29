import { Container } from "react-bootstrap";
import NuevaReparacion from "../../reparacion/components/NuevaReparacion";
import Horario from "./Horario";
import { useState, useEffect } from "react";
import { Spinner } from "react-bootstrap";
import backend from "../../../backend";

const PaginaHorario = () => {

    var fechaHoy = new Date();
    const [reparaciones, setReparaciones] = useState(null);
    const [fechaReparaciones, setFechaReparaciones] = useState(fechaHoy.getDate() + "-" +
        fechaHoy.getMonth() + "-" + fechaHoy.getFullYear());

    const getFechaFormateada = (fecha) => {
        var fechaFormatoDate = new Date(fecha)
        return fechaFormatoDate.getDate() + "-" + fechaFormatoDate.getMonth()
            + "-" + fechaFormatoDate.getFullYear()
    }

    useEffect(() => {
        backend.tallerService.buscarAsistenciasPorFecha(
            fechaReparaciones,
            resultado => setReparaciones(resultado))
    }, [fechaReparaciones])

    return (
        <Container>
            <div>
                <input
                    type="date"
                    value={fechaReparaciones}
                    onChange={(nuevaFecha) =>
                        setFechaReparaciones(getFechaFormateada(nuevaFecha))} />
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