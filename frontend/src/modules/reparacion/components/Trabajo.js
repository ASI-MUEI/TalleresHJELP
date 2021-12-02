import Container from "react-bootstrap/Container";
import BuscarReparaciones from "./BuscarReparaciones";
import {useParams} from "react-router";
import React, {useEffect, useState} from "react";
import backend from "../../../backend"
import {Badge, Button, Jumbotron, Spinner} from "react-bootstrap";
import {FormattedDate, FormattedMessage} from "react-intl";
import { jsPDF } from "jspdf";

const Trabajo = () => {

    const [trabajo, setTrabajo] = useState(null);
    const [factura, setFactura] = useState("");
    const {idTrabajo} = useParams();
    const doc = new jsPDF();

    const cerrarTrabajo = () => {
        backend.tallerService.cambiarEstadoTrabajo(idTrabajo, "Cerrado");
        window.location.reload()
    }

    const createPDF = result => {
        if (result) {
            doc.setFontSize(11);
            doc.text(result, 5, 10);
        }
    }

    const descargarFactura = () => {
        createPDF(factura.cuerpoFactura)
        doc.save(`${trabajo.nombre}-FACTURA.pdf`);
    }

    const establecerPagado = () => {
        backend.tallerService.cambiarEstadoTrabajo(idTrabajo, "Pagado");
        window.location.reload()
    }

    useEffect(() =>{
        backend.tallerService.buscarTrabajoPorId(
            idTrabajo,
            resultado => setTrabajo(resultado)
        );
    }, [idTrabajo])

    useEffect(() =>{
        if(trabajo !== null && trabajo.estado !== "Abierto"){
            backend.tallerService.getFactura(idTrabajo, result => setFactura(result))
        }
    },[trabajo])

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
                {
                    trabajo.estado === "Cerrado" || trabajo.estado === "Pagado"?
                        null
                    :
                        <Button variant={"primary"} onClick={event => cerrarTrabajo()}>
                            <FormattedMessage id={"trabajo.cerrar"}/>
                        </Button>
                }

                &nbsp;&nbsp;&nbsp;&nbsp;
                {
                    trabajo.estado !== "Abierto"?
                        <Button variant={"primary"} type={"submit"} onClick={event => descargarFactura()}>
                            <FormattedMessage id={"trabajo.verFactura"}/>
                        </Button>
                    :
                        null
                }
                &nbsp;&nbsp;&nbsp;&nbsp;
                {
                    trabajo.estado === "Cerrado"?
                        <Button variant={"primary"} onClick={event => establecerPagado()}>
                            <FormattedMessage id={"trabajo.establecerPagado"}/>
                        </Button>
                    :
                        null
                }
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
                        trabajo.estado === "Pagado"?
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