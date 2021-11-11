import React, {useState} from "react";
import backend from '../../../backend';
import {Button, Form} from "react-bootstrap";
import {FormattedMessage} from "react-intl";
import Container from "react-bootstrap/Container";
import {Link} from "react-router-dom";
import {useHistory} from "react-router";
import Errors from "../../commons/components/Errors";

const RegistrarMecanico = () => {

    const rol = 1;
    const [dni, setDni] = useState("");
    const [nombreUsuario, setNombreUsuario] = useState("");
    const [apellidosUsuario, setApellidosUsuario] = useState("");
    const [backendErrors, setBackendErrors] = useState(null);

    const history = useHistory();

    const handleSubmit = event => {
        event.preventDefault();
        backend.userService.signUp({
                nombreUsuario: nombreUsuario.trim(),
                apellidosUsuario: apellidosUsuario.trim(),
                dni,
                rolUsuario: rol
            },
            () => history.push("/"),
            errors => setBackendErrors(errors)
        );
    }

    return(
        <Container>
            <h3 className={"centeredParagraph"}>
                <FormattedMessage id={"registrarUsuario.Mecanico"}/>
            </h3>
            <div>
                <Link to="/usuarios/registrar"><FormattedMessage id={"back"}/></Link>
            </div>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
            <br/>
            <div>
                <Form onSubmit={handleSubmit}>
                    <Form.Group>
                        <Form.Label><FormattedMessage id='user.SignUp.Mecanico.Dni'/></Form.Label>
                        <Form.Control type="text" value={dni} onChange={e => setDni(e.target.value)}/>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label><FormattedMessage id='user.SignUp.Mecanico.Name'/></Form.Label>
                        <Form.Control type="text" value={nombreUsuario} onChange={e => setNombreUsuario(e.target.value)}/>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label><FormattedMessage id='user.SignUp.Mecanico.Surname'/></Form.Label>
                        <Form.Control type="text" value={apellidosUsuario}
                                      onChange={e => setApellidosUsuario(e.target.value)}/>
                    </Form.Group>
                    <Button variant="primary" type="submit">
                        <FormattedMessage id={'user.SignUp.SignUp'}/>
                    </Button>
                </Form>
            </div>
        </Container>
    )
}

export default RegistrarMecanico;