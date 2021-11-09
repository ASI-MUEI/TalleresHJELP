import {Button, Container, Form} from "react-bootstrap";
import {FormattedMessage, useIntl} from "react-intl";
import {Multiselect} from 'multiselect-react-dropdown';
import React, {useState} from "react";
import {Link} from "react-router-dom";

const AnadirReparacion = () => {

    const [matricula, setMatricula] = useState("")
    const [mecanicos, setMecanicos] = useState("")
    const [precio, setPrecio] = useState(0)
    const [fecha, setFecha] = useState("")
    const [elevador, setElevador] = useState("")
    const [horasDeTrabajo, setHorasDeTrabajo] = useState("")
    const [duracionEstimada, setDuracionEstimada] = useState("")
    const [peritaje, setPeritaje] = useState(false)
    const [descripcion, setDescripcion] = useState("")

    const [listaMatricula, setListaMatricula] = useState([])
    const [listaMecanicos, setListaMecanicos] = useState([])
    const [listaElevadores, setListaElevadores] = useState([])
    const [listaHorarios, setListaHorarios] = useState([])

    const intl = useIntl();

    const handleSubmit = event =>{
        // TODO: conexion backend
    }

    return (
        <Container>
            <br/>
            <h4 className="centeredParagraph">{intl.formatMessage({id: 'paginaHorario.nuevaReparacion.nuevaReparacion'})}</h4>
            <br/>
            <Form onSubmit={handleSubmit}>
                <div className="divFlexDirectionColumn">
                    <div className="añadirReparacionDiv1">
                        <Multiselect
                            placeholder={intl.formatMessage({id: 'paginaHorario.nuevaReparacion.selectorMatrícula'})}
                            isObject={false}
                            options={listaMatricula}
                            showArrow="true"
                            selectionLimit ={1}
                            onSelect={selectedList => setMatricula(Array.from(selectedList))}
                            onRemove={selectedList => setMatricula(Array.from(selectedList))}
                        />
                        &nbsp;
                        <br/>
                        <Link
                            to="/trabajos/nuevo"><FormattedMessage id={'paginaHorario.nuevaReparacion.CrearTrabajo'}/>
                        </Link>
                    </div>
                    <div className="añadirReparacionDiv1">
                        <Multiselect
                            placeholder={intl.formatMessage({id: 'paginaHorario.nuevaReparacion.selectorMecanicos'})}
                            isObject={false}
                            options={listaMecanicos}
                            showArrow="true"
                            onSelect={selectedList => setMecanicos(Array.from(selectedList))}
                            onRemove={selectedList => setMecanicos(Array.from(selectedList))}
                        />
                    </div>
                    <div className="añadirReparacionDiv1">
                        <Form.Group>
                            <Form.Control
                                type="number"
                                min="0"
                                max="20000"
                                placeholder={intl.formatMessage({id: 'paginaHorario.nuevaReparacion.selectorPrecio'})}
                                value={precio}
                                onChange={event => setPrecio(event.target.value)}
                            />
                        </Form.Group>
                    </div>
                    <br/>
                    <br/>
                </div>

                <br/>

                <div className="divFlexDirectionColumn">
                    <div className="añadirReparacionDiv1">
                        <input
                            type="date"
                            value={fecha}
                            onChange={event => setFecha(event.target.value)}
                        />
                    </div>
                    <div className="añadirReparacionDiv1">
                        <Multiselect
                            placeholder={intl.formatMessage({id: 'paginaHorario.nuevaReparacion.selectorElevador'})}
                            isObject={false}
                            options={listaElevadores}
                            showArrow="true"
                            selectionLimit ={1}
                            onSelect={selectedList => setElevador(Array.from(selectedList))}
                            onRemove={selectedList => setElevador(Array.from(selectedList))}
                        />
                    </div>
                    <div className={"añadirReparacionDiv1"}>
                        <Multiselect
                            placeholder={intl.formatMessage({id: 'paginaHorario.nuevaReparacion.selectorHorario'})}
                            isObject={false}
                            options={listaHorarios}
                            showArrow="true"
                            onSelect={selectedList => setHorasDeTrabajo(Array.from(selectedList))}
                            onRemove={selectedList => setHorasDeTrabajo(Array.from(selectedList))}
                        />
                    </div>
                    <br/>
                </div>


                <br/>
                <div className="divFlexDirectionColumn">
                    <div className="añadirReparacionDiv1">
                        <Form.Group>
                            <Form.Control
                                type="number"
                                min="0"
                                max="1000"
                                placeholder={intl.formatMessage({id: 'paginaHorario.nuevaReparacion.horasEstimadas'})}
                                value={duracionEstimada}
                                onChange={event => setDuracionEstimada(event.target.value)}
                            />
                        </Form.Group>
                    </div>
                    <div className="añadirReparacionDiv1">
                        <Form.Check
                            type="checkbox"
                            label={intl.formatMessage({id: 'paginaHorario.nuevaReparacion.checkBoxPeritaje'})}
                            value={peritaje}
                            onChange={event => setPeritaje(event.target.value)}
                        />
                    </div>
                </div>
                <br/>
                <div>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                        <Form.Control
                            as="textarea"
                            rows={3}
                            maxlength={"1000"}
                            value={descripcion}
                            onChange={event => setDescripcion(event.target.value)}
                            placeholder={intl.formatMessage({id: 'trabajos.nuevo.descripcionTrabajo'})}
                        />
                    </Form.Group>
                </div>
            </Form>
            <br/>
            <div className="d-flex justify-content-center">
                <Button variant="success" type="submit">
                    {intl.formatMessage({id: 'app.Commons.Save'})}
                </Button>
            </div>
            <br/><br/><br/><br/>
        </Container>


    )

}

export default AnadirReparacion;