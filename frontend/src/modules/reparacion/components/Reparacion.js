import {Badge, Button, Container, Form, Jumbotron, Spinner} from "react-bootstrap";
import {useHistory, useParams} from "react-router";
import {FormattedDate, FormattedMessage, useIntl} from "react-intl";
import React, {useEffect, useState} from "react";
import backend from "../../../backend"
import BuscarPartesReparacion from "./BuscarPartesReparacion";
import {Multiselect} from "multiselect-react-dropdown";

const Reparacion = () => {

    const {idReparacion} = useParams();
    const [datosReparacion, setDatosReparacion] = useState(null);
    const [listaPiezas, setListaPiezas] = useState([]);
    const [pieza, setPieza] = useState(null);
    const [numeroPiezas, setNumeroPiezas] = useState(0);

    const intl = useIntl();
    const history = useHistory();

    const cabecera = () => {
        return (
            <div>
                <h2 className="centeredParagraph"><FormattedMessage id={"reparacion.cabecera"}/></h2>
                <br/>
            </div>
        )
    }

    const anadirPieza = () => {
        if(pieza !== null){
            backend.tallerService.asignarPiezaAsistencia(
                {
                    idAsistencia: idReparacion,
                    idPieza: pieza[0].idPieza,
                    numeroPiezas: numeroPiezas
                }
            )
        }
    }

    const cambiarRetraso = () => {
        backend.tallerService.cambiarRetraso(idReparacion, 'null', result => window.location.reload())

    }

    useEffect(() => {
        backend.tallerService.buscarReparacionPorId(
            idReparacion,
            resultado => setDatosReparacion(resultado)
        )
    }, [idReparacion])

    useEffect(() =>{
        backend.tallerService.getAllPiezas(
            piezas => setListaPiezas(piezas)
        )
    },[])

    if (datosReparacion === null) {
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


    return (
        <Container>
            {cabecera()}
            <div className={"divFlexDirectionColumn "}>
                <div className={"center"}>
                    <form onSubmit={() => history.push(`/reparacion/${datosReparacion.idAsistencia}/atraso`)}>
                        <Button variant={"info"}>
                            <a className={"aWhite"} href={datosReparacion.manualVehiculo}><FormattedMessage id={"reparacion.verManual"}/></a>
                        </Button>
                    </form>
                </div>
                &nbsp;
                <div className={"center"}>
                    {
                        datosReparacion.retrasada?
                            <Button variant={"success"} onClick={event => cambiarRetraso()}>
                                <FormattedMessage id={'reparacion.marcarNoRetrasada'}/>
                            </Button>
                        :
                            <form onSubmit={() => history.push(`/reparaciones/${datosReparacion.idAsistencia}/atraso`)}>
                                <Button type={"submit"} variant={"danger"}>
                                    <FormattedMessage id={"reparacion.marcarRetrasada"}/>
                                </Button>
                            </form>
                    }

                </div>
            </div>

            <br/>

            <Jumbotron fluid>
                <Container>
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.trabajo'/>:
                    </h5> {datosReparacion.idTrabajo}
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='user.SignUp.Cliente.Name'/>:
                    </h5> {datosReparacion.nombreCliente}
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.tipo'/>:
                    </h5> {datosReparacion.tipoReparacion}
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.modeloVehiculo'/>:
                    </h5> {datosReparacion.modeloDeVehiculo}
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.elevador'/>:
                    </h5> {datosReparacion.nombreElevador}
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.fecha'/>:</h5> <FormattedDate
                    value={datosReparacion.fecha}/>
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='paginaHorario.nuevaReparacion.Descripcion'/>:
                    </h5> {datosReparacion.descripcion}
                    <h5>Lista de mecanicos:</h5>
                    {
                        datosReparacion.mecanicos.map(mecanico =>
                            <h6> + {mecanico.nombreMecanico}</h6>
                        )
                    }
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.duracion'/>:
                    </h5> {datosReparacion.duracionEstimada}h
                    <br/>
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.precio'/>:</h5> &nbsp;
                    <Badge variant={"warning"}>{datosReparacion.precio}€</Badge>
                </Container>
            </Jumbotron>
            <h3 className={"center"}><FormattedMessage id={"reparacion.listadoPiezas"}/></h3>
            <br/>
            <BuscarPartesReparacion idReparacion={datosReparacion.idAsistencia}/>
            <br/><br/><br/><br/><br/><br/><br/><br/>
            <div>
                <h3 className={"center"}><FormattedMessage id={"reparacion.añadirPieza"}/></h3>
                <br/>
                <Form onSubmit={event => anadirPieza()}>
                    <Multiselect
                        placeholder={intl.formatMessage({id: 'reparacion.añadirPieza'})}
                        isObject={true}
                        displayValue={"nombre"}
                        options={listaPiezas}
                        showArrow="true"
                        selectionLimit={1}
                        onSelect={selectedList => setPieza(Array.from(selectedList))}
                        onRemove={selectedList => setPieza(Array.from(selectedList))}
                    />
                    <br/>
                    <div className={"center"}>
                        <FormattedMessage id={"reparacion.numeroPiezas"}/>:&nbsp;
                        <input type={"number"} value={numeroPiezas}
                               onChange={event => setNumeroPiezas(event.target.value)} min={1}
                        />
                    </div>

                    <br/>

                    <Button type={"submit"} className={"center"} variant={"success"}>
                        <FormattedMessage id={"app.Commons.Save"}/>
                    </Button>
                </Form>

            </div>
            <br/><br/><br/><br/><br/><br/><br/><br/>

        </Container>

    )
}

export default Reparacion;