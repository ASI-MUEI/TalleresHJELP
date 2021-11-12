import React, {useEffect, useState} from "react";
import {Alert, Spinner} from "react-bootstrap";
import {FormattedMessage} from "react-intl";
import Container from "react-bootstrap/Container";
import Trabajos from "./Trabajos";
import Pager from "../../commons/components/Pager"
import backend from "../../../backend"

const BuscarTrabajos = () => {

    const [trabajos, setTrabajos] = useState(null)

    // Paginacion
    var page = 0
    const size = 5

    useEffect(() => {
        backend.tallerService.buscarTrabajos(
            page,
            size,
            (resultado) => setTrabajos(resultado)
        )
    }, [page])

    const cabecera = () => {

        return (
            <h3 className={"centeredParagraph"}><FormattedMessage id={'trabajos.apartado'}/></h3>
        )
    }

    // Mientras no se inicializa, se devuelve un Spinner
    if (trabajos === null) {
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

    // Si no hay trabajos
    if (trabajos.items.length === 0) {
        return (
            <Container>
                {cabecera()}
                <br/>
                <Alert variant={"primary"}>
                    <FormattedMessage id={'trabajo.vacia'}/>
                </Alert>
            </Container>

        )
    }

    return (
        <div>
            <Trabajos listaTrabajos={trabajos.items}/>
            <Pager
                back={{
                    enabled: page >= 1,
                    onClick: () => page -= 1
                }}
                next={{
                    enabled: trabajos.existMoreItems,
                    onClick: () => page += 1
                }}/>
        </div>
    )
}

export default BuscarTrabajos;