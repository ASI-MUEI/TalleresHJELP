import {Badge, Button, Container, Jumbotron, Spinner} from "react-bootstrap";
import {useParams} from "react-router";
import {FormattedDate, FormattedMessage} from "react-intl";
import {useEffect, useState} from "react";
import backend from "../../../backend"
import BuscarPartesReparacion from "./BuscarPartesReparacion";

const Reparacion = () => {

    const {idReparacion} = useParams();
    const [datosReparacion, setDatosReparacion] = useState(null);

    const cabecera = () => {
        return (
            <div>
                <h2 className="centeredParagraph"><FormattedMessage id={"reparacion.cabecera"}/></h2>
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
            <div className={"divFlexDirectionColumn center"}>
                <Button variant={"info"}>
                    <FormattedMessage id={"reparacion.verManual"}/>
                </Button>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <Button variant={"danger"}>
                    <FormattedMessage id={"reparacion.marcarRetrasada"}/>
                </Button>
            </div>
            <br/>
            <Jumbotron fluid>
                <Container>
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.trabajo'/>:
                    </h5> {datosReparacion.idTrabajo}
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='user.SignUp.Cliente.Name'/>:
                    </h5> {datosReparacion.nombreCliente}
                    <br/>
                    {/*TODO: Tipo de reparacion*/}
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.tipo'/>:
                    </h5> {"Cambio aceite"}
                    <br/>
                    {/*TODO: Modelo de vehículo*/}
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.modeloVehiculo'/>:
                    </h5> {"BMW Serie 1"}
                    <br/>
                    {/*TODO: Elevador*/}
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.elevador'/>:
                    </h5> {"Elevador 1"}
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.fecha'/>:</h5> <FormattedDate
                    value={datosReparacion.fecha}/>
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='paginaHorario.nuevaReparacion.Descripcion'/>:
                    </h5> {datosReparacion.descripcion}
                    <h5>Lista de mecanicos:</h5>
                    {
                        datosReparacion.mecanicos.map(mecanico =>
                            <h6> + {mecanico.nombreMecanico}</h6>
                        )
                    }
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.duracion'/>:
                    </h5> {datosReparacion.duracionEstimada}h
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.precio'/>:</h5> &nbsp;
                    <Badge variant={"warning"}>{datosReparacion.precio}€</Badge>
                </Container>
            </Jumbotron>
            <h3 className={"center"}><FormattedMessage id={"reparacion.listadoPiezas"}/></h3>
            <br/>
            <BuscarPartesReparacion idReparacion={datosReparacion.idAsistencia}/>
            <br/><br/><br/><br/><br/><br/><br/><br/>

        </Container>

    )
}

export default Reparacion;