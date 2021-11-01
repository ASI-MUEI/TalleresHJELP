import React, {useState} from "react";
import Container from "react-bootstrap/Container";
import {Button, Form} from "react-bootstrap";
import {FormattedMessage, useIntl} from "react-intl";
import RegistrarCliente from "../../user/components/RegistrarCliente";
import {Multiselect} from "multiselect-react-dropdown";


const AnadirTrabajo = () =>{

    const intl = useIntl()

    const [nombreTrabajo, setNombreTrabajo] = useState("")
    const [matricula, setMatricula] = useState("")
    const [descripcion, setDescripcion] = useState("")

    const [anadirCliente, setAnadirCliente] = useState(false);

    const [listaMatricula, setListaMatricula] = useState([]);

    const handleSubmit = event =>{
        //TODO: conectar backend
    }

    return(
        <Container>
            <h3 className={"centeredParagraph"}>
                <FormattedMessage id={"trabajos.nuevo.registrarTrabajo"}/>
            </h3>
            <br/>
            <Form onSubmit={handleSubmit}>
                <Form.Group>
                    <Form.Control
                        type={"text"}
                        value={nombreTrabajo}
                        onChange={event => setNombreTrabajo(event.target.value)}
                        placeholder={intl.formatMessage({id: 'trabajos.nuevo.nombreTrabajo'})}
                    />
                </Form.Group>
                <Form.Group>
                    <Multiselect
                        placeholder={intl.formatMessage({id: 'paginaHorario.nuevaReparacion.selectorMatrícula'})}
                        isObject={false}
                        options={listaMatricula}
                        showArrow="true"
                        selectionLimit ={1}
                        onSelect={selectedList => setMatricula(Array.from(selectedList))}
                        onRemove={selectedList => setMatricula(Array.from(selectedList))}
                    />
                    <br/>
                    <Button
                        variant="secondary"
                        value={anadirCliente}
                        onClick={event => setAnadirCliente(!anadirCliente)}
                    >
                        <FormattedMessage id={'trabajos.nuevo.registrarCliente'}/>
                    </Button>
                </Form.Group>

                <div hidden={!anadirCliente}>
                    <RegistrarCliente/>
                </div>
                <br/>
                <Form.Group>
                    <Form.Control
                        as="textarea"
                        rows={3}
                        maxlength={"1000"}
                        value={descripcion}
                        onChange={event => setDescripcion(event.target.value)}
                        placeholder={intl.formatMessage({id: 'trabajos.nuevo.descripcionTrabajo'})}
                    />
                </Form.Group>
                <Button type={"submit"}>
                    <FormattedMessage id={'trabajos.nuevo.registrarTrabajo'}/>
                </Button>
            </Form>
        </Container>
    )

}

export default AnadirTrabajo;