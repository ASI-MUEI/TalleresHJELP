import React, {useState} from "react";
import backend from '../../../backend';
import {Button, Form} from "react-bootstrap";
import {FormattedMessage} from "react-intl";
import Container from "react-bootstrap/Container";
import {Link} from "react-router-dom";

const RegistrarCliente = () => {

    const rol = 2;
    const [dni, setDni] = useState("");
    const [nombreUsuario, setNombreUsuario] = useState("");
    const [apellidosUsuario, setApellidosUsuario] = useState("");

    const handleSubmit = event => {

        event.preventDefault();
        //TODO llamar a backend
    }

    return(
        <Container>
            <h3 className={"centeredParagraph"}>
                <FormattedMessage id={"registrarUsuario.Cliente"}/>
            </h3>
            <div>
                <Link to="/usuarios/registrar"><FormattedMessage id={"back"}/></Link>
            </div>
            <br/>
            <div>
                <Form onSubmit={handleSubmit}>
                    <Form.Group>
                        <Form.Label><FormattedMessage id='user.SignUp.Cliente.Dni'/></Form.Label>
                        <Form.Control type="text" value={dni} onChange={e => setDni(e.target.value)}/>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label><FormattedMessage id='user.SignUp.Cliente.Name'/></Form.Label>
                        <Form.Control type="text" value={nombreUsuario} onChange={e => setNombreUsuario(e.target.value)}/>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label><FormattedMessage id='user.SignUp.Cliente.Surname'/></Form.Label>
                        <Form.Control type="text" value={apellidosUsuario}
                                      onChange={e => setApellidosUsuario(e.target.value)}/>
                    </Form.Group>
                    <Button variant="primary" type="submit">
                        <FormattedMessage id={'trabajos.nuevo.registrarCliente'}/>
                    </Button>
                </Form>
            </div>
        </Container>
    )
}

export default RegistrarCliente;