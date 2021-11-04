import React, {useEffect, useState} from "react";
import {Alert, Form, Spinner} from "react-bootstrap";
import {FormattedMessage} from "react-intl";
import Container from "react-bootstrap/Container";
import Trabajos from "./Trabajos";
import Pager from "../../commons/components/Pager"

const BuscarReparaciones = () =>{

    // TODO: Deberia ser nulo para cargar spinner antes de la primera llamada a backend
    const [reparaciones, setReparaciones] = useState(null)

    // Paginacion
    var page = 0
    const size = 5

    useEffect(() => {
        // TODO: conexión con backend para pedir reparaciones
    }, [page])

    const cabecera = () => {

        return(
            <h3 className={"centeredParagraph"}><FormattedMessage id={'reparaciones.asociadas'}/></h3>
        )
    }

    // Mientras no se inicializa, se devuelve un Spinner
    if(reparaciones === null){
        return(
            <Container>
                {cabecera()}
                <br/>
                <div className="center">
                    <Spinner animation="border" role="status">
                        <span className="visually-hidden" />
                    </Spinner>
                </div>
            </Container>

        )
    }

    // Si no hay reparaciones
    if(reparaciones.length === 0){
        return(
            <Container>
                {cabecera()}
                <br/>
                <Alert variant={"primary"}>
                    <FormattedMessage id={'reparacion.vacia'}/>
                </Alert>
            </Container>

        )
    }

    return(
        <div>
            <Trabajos datosTrabajos={reparaciones.result.items}/>
            <Pager
                back={{
                    enabled: reparaciones.criteria.page >= 1,
                    onClick: () => page -=1}}
                next={{
                    enabled: reparaciones.result.existMoreItems,
                    onClick: () => page +=1}}/>
        </div>
    )
}

export default BuscarReparaciones;