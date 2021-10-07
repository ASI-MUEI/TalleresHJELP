import { Container } from "react-bootstrap";
import NuevaReparacion from "../../reparacion/components/NuevaReparacion";
import Horario from "./Horario";

const PaginaHorario = () => {

    return(
        <Container>
            <Horario/>
            <br/>
            <hr/>
            <NuevaReparacion/>
        </Container>
    )
}

export default PaginaHorario;