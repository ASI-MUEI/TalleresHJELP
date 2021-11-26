import {Badge, Button, Container, Jumbotron, Spinner} from "react-bootstrap";
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
        //TODO: método que añade una pieza a una reparacion
    }

    useEffect(() => {
        backend.tallerService.buscarReparacionPorId(
            idReparacion,
            resultado => setDatosReparacion(resultado)
        )
    }, [idReparacion])

    useEffect(() =>{
        //TODO: método que mande buscar el listado de piezas
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
                            <FormattedMessage id={"reparacion.verManual"}/>
                        </Button>
                    </form>
                </div>
                &nbsp;
                <div className={"center"}>
                    <form onSubmit={() => history.push(`/reparaciones/${datosReparacion.idAsistencia}/atraso`)}>
                        <Button type={"submit"} variant={"danger"}>
                            <FormattedMessage id={"reparacion.marcarRetrasada"}/>
                        </Button>
                    </form>
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
                    {/*TODO: Tipo de reparacion*/}
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.tipo'/>:
                    </h5> {"Cambio aceite"}
                    <br/>
                    {/*TODO: Modelo de vehículo*/}
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.modeloVehiculo'/>:
                    </h5> {"BMW Serie 1"}
                    <br/>
                    {/*TODO: Elevador*/}
                    <h5 className="hWithoutLineBreak"><FormattedMessage id='reparacion.elevador'/>:
                    </h5> {"Elevador 1"}
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
                           onChange={event => setNumeroPiezas(event.target.value)} min={0}
                    />
                </div>

                <br/>
                <Button className={"center"} variant={"success"} onClick={anadirPieza()}>
                    <FormattedMessage id={"app.Commons.Save"}/>
                </Button>
            </div>
            <br/><br/><br/><br/><br/><br/><br/><br/>

        </Container>

    )
}

export default Reparacion;