import { Jumbotron } from "react-bootstrap";
import { Container } from "react-bootstrap"
import { BMW_SERIES_1 } from "../../commons/constants";

const Reparacion = () => {

    return (
        <Container>
            <h2 className="centeredParagraph">Datos de la reparación</h2>
            <div className="center">
                <img className="center" alt="Coche" className="photographyDetail" src={BMW_SERIES_1} />
            </div>
            <Jumbotron fluid>
                <Container>
                    <h5 className="hWithoutLineBreak" >Elevador:</h5> Elevador 1
                    <br />
                    <h5 className="hWithoutLineBreak" >Vehículo:</h5> BMW Serie 1 - Paquete M
                    <br />
                    <h5 className="hWithoutLineBreak" >Matrícula:</h5> 9265LLC
                    <br />
                    <h5 className="hWithoutLineBreak" >Cliente:</h5> Juan Manuel Díaz Ayuso
                    <br/>
                    <h5 className="hWithoutLineBreak" >Descripción:</h5> Chasis/Dirección: Se comprueba el estado de la suspensión, (amortiguadores, muelles, puntos de anclaje…) y de la dirección (rótulas, bomba de asistencia…) |
Motor/sistemas anticontaminación: Se centran en detectar posibles fallos de motor, incluido el sistema de escape, y sistema de transmisión (caja de cambios, diferencial..)
 | Carrocería/interior: Se vigilar su deterioro (corrosión, faros)… así como el cinturón de seguridad y desjustes del salpicadero.
 | Sistema de frenos: Para comprobar su eficacia y posibles fallos en el sistema de frenado (pastillas, discos, servofreno, líquidos…)
 | Electricidad/electrónica: Se localizan posibles fallos eléctricos (luces en el cuadro de mandos, cierre, climatizador..)
                </Container>
            </Jumbotron>
        </Container>
    )
}

export default Reparacion;