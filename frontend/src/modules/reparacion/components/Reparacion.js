import {Container, Jumbotron, Spinner} from "react-bootstrap";
import {useParams} from "react-router";
import {FormattedDate, FormattedMessage} from "react-intl";
import {useEffect, useState} from "react";
import backend from "../../../backend"

const Reparacion = () => {

    const {idReparacion} = useParams();
    const [datosReparacion, setDatosReparacion] = useState(null);

    const cabecera = () => {
        return (
            <div>
                <h2 className="centeredParagraph">Datos de la reparación</h2>
                <br/>
            </div>
        )
    }

    useEffect(() => {
        backend.tallerService.buscarReparacionPorId(
            idReparacion,
            resultado => setDatosReparacion(resultado)
        )
    }, [idReparacion])

    if (datosReparacion === null) {
        //TODO: activar esto
        return (
            <Container>
                {cabecera()}
                <br/>
                <div className="center">
                    <Spinner animation="border" role="status">
                        <span className="visually-hidden"/>
                    </Spinner>
                </div>
            </Container>
        )
    }


    return (
        <Container>
            {cabecera()}
            <Jumbotron fluid>
                <Container>
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.trabajo'/>:
                    </h5> {datosReparacion.matricula}
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='user.SignUp.Cliente.Name'/>:
                    </h5> {datosReparacion.nombreCliente}
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.fecha'/>:</h5> <FormattedDate
                    value={datosReparacion.fecha}/>
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.duracion'/>:
                    </h5> {datosReparacion.duracionEstimada}
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.precio'/>:
                    </h5> {datosReparacion.precio}€
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='paginaHorario.nuevaReparacion.Descripcion'/>:
                    </h5> {datosReparacion.descripcion}
                    <h5>Lista de mecanicos:</h5>
                    {
                        datosReparacion.mecanicos.map(mecanico =>
                            <h5>- {mecanico.nombreMecanico}</h5>
                        )
                    }
                </Container>
            </Jumbotron>
            {/* <div className="center">
                <img className="center" alt="Coche" className="photographyDetail" src={BMW_SERIES_1} />
            </div> */}
        </Container>
    )
}

export default Reparacion;