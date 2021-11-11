import Container from "react-bootstrap/Container";
import BuscarReparaciones from "./BuscarReparaciones";
import {useParams} from "react-router";
import React, {useEffect, useState} from "react";
import backend from "../../../backend"
import {Jumbotron, Spinner} from "react-bootstrap";
import {FormattedDate, FormattedMessage} from "react-intl";

const Trabajo = () => {

    const [trabajo, setTrabajo] = useState(null);

    const {idTrabajo} = useParams();

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
            <Jumbotron>
                <div>
                    <h5 className={'hWithoutLineBreak'}><FormattedMessage id={'trabajo.nombre'}/></h5>: {trabajo.nombre}
                </div>
                <div>
                    <h5 className={'hWithoutLineBreak'}><FormattedMessage id={'trabajo.descripcion'}/></h5>: {trabajo.descripcion}
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
            </Jumbotron>

            <div>
                <BuscarReparaciones/>
            </div>
        </Container>
    )

}
export default Trabajo;