import React, {useEffect, useState} from "react";
import {Alert, Spinner} from "react-bootstrap";
import {FormattedMessage} from "react-intl";
import Container from "react-bootstrap/Container";
import Pager from "../../commons/components/Pager"
import backend from "../../../backend"
import {useParams} from "react-router";
import Reparaciones from "./Reparaciones";
import PartesReparacion from "./PartesReparacion";


const BuscarPartesReparacion = ({idReparacion}) => {

    // TODO: Deberia ser nulo para cargar spinner antes de la primera llamada a backend
    const [partesReparacion, setPartesReparacion] = useState(null)

    // Paginacion
    var page = 0
    const size = 5

    useEffect(() => {
        backend.tallerService.buscarPiezasReparacion(
            idReparacion,
            page,
            size,
            result => setPartesReparacion(result)
        )
        // eslint-disable-next-line
    }, [page])


    // Mientras no se inicializa, se devuelve un Spinner
    if (partesReparacion === null) {
        return (
            <Container>
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
    if (partesReparacion.items.length === 0) {
        return (
            <Container>
                <br/>
                <Alert variant={"primary"}>
                    <FormattedMessage id={'reparacion.sinPiezas'}/>
                </Alert>
            </Container>

        )
    }

    return (

        <div>
            <PartesReparacion listaPartes={partesReparacion.items}/>
            <Pager
                back={{
                    enabled: page >= 1,
                    onClick: () => page -= 1
                }}
                next={{
                    enabled: partesReparacion.existMoreItems,
                    onClick: () => page += 1
                }}/>
        </div>
    )
}

export default BuscarPartesReparacion;