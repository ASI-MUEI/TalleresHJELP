import Container from "react-bootstrap/Container";
import {Button, Form} from "react-bootstrap";
import React, {useState} from "react";
import {FormattedMessage, useIntl} from "react-intl";
import {useParams} from "react-router";
import backend from "../../../backend";
import {useHistory} from "react-router";

const AnadirAtraso = () => {

    const [motivo, setMotivo] = useState();
    const intl = useIntl()
    const history = useHistory();

    const {idReparacion} = useParams()

    const handleSubmit = (event) => {

        event.preventDefault()
        backend.tallerService.cambiarRetraso(
            idReparacion,
            motivo,
            result => history.push(`/reparaciones/${idReparacion}`))
    }


    return(
        <Container>
            <h5 className={"center"}><FormattedMessage id={"crearRetraso.titulo"}/></h5>
            <br/>
            <Form onSubmit={event => handleSubmit(event)}>
                <Form.Group>
                    <Form.Control
                        as="textarea"
                        rows={3}
                        maxlength={"1000"}
                        value={motivo}
                        onChange={event => setMotivo(event.target.value)}
                        placeholder={intl.formatMessage({id: 'crearRetraso.motivo'})}
                        required
                    />
                </Form.Group>
                <br/>
                <Button className={"center"} variant="success" type="submit">
                    <FormattedMessage id={'app.Commons.Save'}/>
                </Button>
            </Form>
        </Container>
    )
}

export default AnadirAtraso;