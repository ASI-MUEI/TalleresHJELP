import React, {useEffect, useState} from "react";
import {Alert, Form, Spinner} from "react-bootstrap";
import {FormattedMessage} from "react-intl";
import Container from "react-bootstrap/Container";
import Trabajos from "./Trabajos";
import Pager from "../../commons/components/Pager"
import backend from "../../../backend"
import {useParams} from "react-router";
import Reparaciones from "./Reparaciones";


const BuscarReparaciones = () =>{

    // TODO: Deberia ser nulo para cargar spinner antes de la primera llamada a backend
    const [reparaciones, setReparaciones] = useState(null)

    const {idTrabajo} = useParams();

    // Paginacion
    var page = 0
    const size = 5

    useEffect(() => {
        backend.tallerService.buscarReparaciones(
            idTrabajo,
            resultado => setReparaciones(resultado)
        )
    }, [page])

    const cabecera = () => {

        return(
            <h3 className={"centeredParagraph"}><FormattedMessage id={'reparacion.asociadas'}/></h3>
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
    if(reparaciones.items.length === 0){
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
            <Reparaciones listaReparaciones={reparaciones.items}/>
            <Pager
                back={{
                    enabled: page >= 1,
                    onClick: () => page -=1}}
                next={{
                    enabled: reparaciones.existMoreItems,
                    onClick: () => page +=1}}/>
        </div>
    )
}

export default BuscarReparaciones;