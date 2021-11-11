import { Jumbotron } from "react-bootstrap";
import { Container } from "react-bootstrap"
import { useParams } from "react-router";
import { FormattedMessage } from "react-intl";
import { BMW_SERIES_1 } from "../../commons/constants";
import { useEffect, useState } from "react";
import { Spinner } from "react-bootstrap";

const Reparacion = () => {

    const {idReparacion} = useParams();
    const [datosReparacion, setDatosReparacion] = useState([]);

    const cabecera = () => {
        <div>
            <h2 className="centeredParagraph">Datos de la reparación</h2>
            <br/>
        </div>
    }

    useEffect(() => {
        //TODO: conectar con backend para cojer datos de reparacion
    }, [])

    if(datosReparacion === null){
        //TODO: activar esto
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



    return (
        <Container>
            <Jumbotron fluid>
                <Container>
                <h5 className="hWithoutLineBreak" >Trabajo:</h5> 9265LLC
                    <br />
                    <h5 className="hWithoutLineBreak" ><FormattedMessage id='reparacion.trabajo'/>:</h5> Elevador 1
                    <br />
                    <h5 className="hWithoutLineBreak" ><FormattedMessage id='user.SignUp.Cliente.Name'/>:</h5> Juan Manuel Díaz Ayuso
                    <br/>
                    <h5 className="hWithoutLineBreak" ><FormattedMessage id='reparacion.fecha'/>:</h5> Juan Manuel Díaz Ayuso
                    <br/>
                    <h5 className="hWithoutLineBreak" ><FormattedMessage id='reparacion.duracion'/>:</h5> Juan Manuel Díaz Ayuso
                    <br/>
                    <h5 className="hWithoutLineBreak" ><FormattedMessage id='reparacion.precio'/>:</h5> Juan Manuel Díaz Ayuso
                    <br/>
                    <h5 className="hWithoutLineBreak" ><FormattedMessage id='paginaHorario.nuevaReparacion.Descripcion'/>:</h5> Chasis/Dirección: Se comprueba el estado de la suspensión, (amortiguadores, muelles, puntos de anclaje…) y de la dirección (rótulas, bomba de asistencia…) |
Motor/sistemas anticontaminación: Se centran en detectar posibles fallos de motor, incluido el sistema de escape, y sistema de transmisión (caja de cambios, diferencial..)
 | Carrocería/interior: Se vigilar su deterioro (corrosión, faros)… así como el cinturón de seguridad y desjustes del salpicadero.
 | Sistema de frenos: Para comprobar su eficacia y posibles fallos en el sistema de frenado (pastillas, discos, servofreno, líquidos…)
 | Electricidad/electrónica: Se localizan posibles fallos eléctricos (luces en el cuadro de mandos, cierre, climatizador..)
                    {
                        //Todo: lista de mecánicos con un map
                    }
                </Container>
            </Jumbotron>
            {/* <div className="center">
                <img className="center" alt="Coche" className="photographyDetail" src={BMW_SERIES_1} />
            </div> */}
        </Container>
    )
}

export default Reparacion;