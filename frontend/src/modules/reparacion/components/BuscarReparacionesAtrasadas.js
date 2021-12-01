import React, {useEffect, useState} from "react";
import {Alert, Spinner} from "react-bootstrap";
import {FormattedMessage} from "react-intl";
import Container from "react-bootstrap/Container";
import Pager from "../../commons/components/Pager"
import backend from "../../../backend"
import {useParams} from "react-router";
import Reparaciones from "./Reparaciones";
import ReparacionesAtrasadas from "./ReparacionesAtrasadas";


const BuscarReparacionesAtrasadas = () => {

    // TODO: Deberia ser nulo para cargar spinner antes de la primera llamada a backend
    const [reparacionesAtrasadas, setReparacionesAtrasadas] = useState(null)

    // Paginacion
    var page = 0
    const size = 5

    useEffect(() => {
        backend.tallerService.buscarReparacionesAtrasadas(result => setReparacionesAtrasadas(result))
        // eslint-disable-next-line
    }, [page])

    const cabecera = () => {

        return (
            <h3 className={"centeredParagraph"}><FormattedMessage id={'reparacion.atrasadas.cabecera'}/></h3>
        )
    }

    // Mientras no se inicializa, se devuelve un Spinner
    if (reparacionesAtrasadas === null) {
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

    // Si no hay reparaciones
    if (reparacionesAtrasadas.items.length === 0) {
        return (
            <Container>
                {cabecera()}
                <br/>
                <Alert variant={"primary"}>
                    <FormattedMessage id={'reparacion.vacia'}/>
                </Alert>
            </Container>

        )
    }


    return (
        <div>
            <ReparacionesAtrasadas listaReparacionesAtrasadas={reparacionesAtrasadas.items}/>
            <Pager
                back={{
                    enabled: page >= 1,
                    onClick: () => page -= 1
                }}
                next={{
                    enabled: reparacionesAtrasadas.existMoreItems,
                    onClick: () => page += 1
                }}/>
        </div>
    )
}

export default BuscarReparacionesAtrasadas;