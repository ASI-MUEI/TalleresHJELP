import Container from "react-bootstrap/Container";
import BuscarReparaciones from "./BuscarReparaciones";
import {useParams} from "react-router";
import React, {useEffect, useState} from "react";
import backend from "../../../backend"
import {Badge, Button, Jumbotron, Spinner} from "react-bootstrap";
import {FormattedDate, FormattedMessage} from "react-intl";

const Trabajo = () => {

    const [trabajo, setTrabajo] = useState(null);
    const {idTrabajo} = useParams();

    const cerrarTrabajo = () => {
        // Todo: conexion backend
    }

    const verFactura = () => {
        // Todo: conexion backend
    }

    const establecerPagado = () => {
        // Todo: conexion backend
    }

    useEffect(() =>{
        backend.tallerService.buscarTrabajoPorId(
            idTrabajo,
            resultado => setTrabajo(resultado)
        );

    }, [idTrabajo])

    const cabecera = () => {
        return (
            <h3 className={"centeredParagraph"}><FormattedMessage id={'trabajo.bienvenida'}/></h3>
        )
    }

    if(trabajo === null){
        return(
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

    return(
        <Container>
            {cabecera()}
            <br/>
            <div className={"divFlexDirectionColumn center"}>
                <Button variant={"primary"} onClick={cerrarTrabajo()}>
                    <FormattedMessage id={"trabajo.cerrar"}/>
                </Button>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <Button variant={"primary"} onClick={verFactura()}>
                    <FormattedMessage id={"trabajo.verFactura"}/>
                </Button>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <Button variant={"primary"} onClick={establecerPagado()}>
                    <FormattedMessage id={"trabajo.establecerPagado"}/>
                </Button>
            </div>
            <br/>
            <Jumbotron>
                <div>
                    <h5 className={'hWithoutLineBreak'}><FormattedMessage id={'trabajo.nombre'}/></h5>: {trabajo.nombre}
                </div>

                <div>
                    <h5 className={'hWithoutLineBreak'}><FormattedMessage id={'trabajo.matricula'}/></h5>: {trabajo.matricula}
                </div>
                <div>
                    <h5 className={'hWithoutLineBreak'}><FormattedMessage id={'trabajo.cliente'}/></h5>: {trabajo.nombreCliente}
                </div>
                <div>
                    <h5 className={'hWithoutLineBreak'}><FormattedMessage id={'trabajo.estado'}/></h5>: {trabajo.estado}
                </div>
                <div>
                    <h5 className={'hWithoutLineBreak'}><FormattedMessage id={'trabajo.fecha'}/></h5>: <FormattedDate value={ new Date(trabajo.fecha)}/>
                </div>
                <div>
                    <h5 className={'hWithoutLineBreak'}><FormattedMessage id={'trabajo.descripcion'}/></h5>: {trabajo.descripcion}
                </div>
                <div>
                    {
                        trabajo.peritaje?
                            <h5>
                                <Badge variant={"warning"}><FormattedMessage id={'trabajo.peritado'}/></Badge>
                            </h5>

                            :
                            <h5>
                                <Badge variant={"info"}><FormattedMessage id={'trabajo.noPeritado'}/></Badge>
                            </h5>

                    }
                </div>
                <div>
                    {
                        trabajo.pagado?
                            <h5>
                                <Badge variant={"success"}><FormattedMessage id={'trabajo.pagado'}/></Badge>
                            </h5>
                            :
                            <h5>
                                <Badge variant={"warning"}><FormattedMessage id={'trabajo.noPagado'}/></Badge>
                            </h5>
                    }
                </div>

            </Jumbotron>

            <div>
                <h3 className={"center"}><FormattedMessage id={"trabajo.listaReparaciones"}/></h3>
                <br/>
                <BuscarReparaciones/>
                <br/><br/><br/><br/><br/><br/>
            </div>
        </Container>
    )

}
export default Trabajo;