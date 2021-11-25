import React, {useEffect, useState} from "react";
import Container from "react-bootstrap/Container";
import {Button, Form} from "react-bootstrap";
import {FormattedMessage, useIntl} from "react-intl";
import {Multiselect} from "multiselect-react-dropdown";
import {Link} from "react-router-dom";
import backend from "../../../backend";
import {useHistory} from "react-router";
import Errors from "../../commons/components/Errors";

const AnadirTrabajo = () => {

    const intl = useIntl()
    const history = useHistory();
    const [nombreTrabajo, setNombreTrabajo] = useState("")
    const [matricula, setMatricula] = useState("")
    const [descripcion, setDescripcion] = useState("")
    const [listaMatricula, setListaMatricula] = useState([])
    const [peritaje, setPeritaje] = useState("")
    const [backendErrors, setBackendErrors] = useState()

    const handleSubmit = event => {

        event.preventDefault()
        backend.tallerService.crearTrabajo(
            {
                nombre: nombreTrabajo,
                descripcion,
                matricula,
                //TODO: enviar peritaje.
            },
            () => history.push("/horario"),
            errors => setBackendErrors(errors)
        )
    }

    useEffect(() => {
        backend.tallerService.recuperarMatriculas(
            resultado => setListaMatricula(resultado)
        )
    }, [])

    return (
        <Container>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
            <h3 className={"centeredParagraph"}>
                <FormattedMessage id={"trabajos.nuevo.registrarTrabajo"}/>
            </h3>
            <br/>
            <div>
                <Link to={"/usuarios/registrar/cli"}>
                    <FormattedMessage id={'trabajos.nuevo.registrarCliente'}/>
                </Link>
            </div>
            <br/>
            <Form onSubmit={handleSubmit}>
                <Form.Group>
                    <Form.Control
                        type={"text"}
                        value={nombreTrabajo}
                        onChange={event => setNombreTrabajo(event.target.value)}
                        placeholder={intl.formatMessage({id: 'trabajos.nuevo.nombreTrabajo'})}
                        required
                    />
                </Form.Group>
                <div className="centeredDiv">
                    <Form.Check
                        type="checkbox"
                        label={intl.formatMessage({id: 'paginaHorario.nuevaReparacion.checkBoxPeritaje'})}
                        value={peritaje}
                    />
                </div>
                <br/>
                <Form.Group>
                    <Multiselect
                        placeholder={intl.formatMessage({id: 'paginaHorario.nuevaReparacion.selectorMatrícula'})}
                        isObject={false}
                        options={listaMatricula}
                        showArrow="true"
                        selectionLimit={1}
                        onSelect={selectedList => setMatricula(Array.from(selectedList)[0])}
                        onRemove={selectedList => setMatricula(Array.from(selectedList)[0])}
                        required
                    />
                    <br/>
                </Form.Group>
                <Form.Group>
                    <Form.Control
                        as="textarea"
                        rows={3}
                        maxlength={"1000"}
                        value={descripcion}
                        onChange={event => setDescripcion(event.target.value)}
                        placeholder={intl.formatMessage({id: 'trabajos.nuevo.descripcionTrabajo'})}
                        required
                    />
                </Form.Group>
                <Button variant="success" type="submit">
                    <FormattedMessage id={'trabajos.nuevo.registrarTrabajo'}/>
                </Button>
            </Form>
        </Container>
    )
}

export default AnadirTrabajo;