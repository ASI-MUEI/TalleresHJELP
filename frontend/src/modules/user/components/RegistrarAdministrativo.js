import {Button, Container, Form} from "react-bootstrap";
import RolSelector from "./RolSelector";
import Errors from "../../commons/components/Errors";
import {FormattedMessage, useIntl} from "react-intl";
import LenguagueSelector from "./LenguagueSelector";
import React, {useState} from "react";
import backend from "../../../backend";
import {Link, useHistory} from "react-router-dom";


const RegistrarAdministrativo = () => {

    const rol = 0;
    const history = useHistory();
    const intl = useIntl();
    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");
    const [repeatPassword, setRepeatPassword] = useState("");
    const [name, setName] = useState("");
    const [surnames, setSurnames] = useState("");
    const [email, setEmail] = useState("");
    const [lenguague, setLenguague] = useState("");
    const [dni, setDni] = useState("");
    const [backendErrors, setBackendErrors] = useState(null);

    const checkPassword = () => {

        if(password !== repeatPassword){
            setBackendErrors({"errorGlobal" : intl.formatMessage({id:'user.SignUp.Error.PasswordsDoNotMatch'})});
            return false;
        }

        return true;
    }

    const handleSubmit = event => {

        event.preventDefault();

        if(checkPassword()){
            backend.userService.signUp(
                {
                    nombreUsuario: userName.trim(),
                    contraseña: password,
                    nombrePilaUsuario: name.trim(),
                    apellidosUsuario: surnames.trim(),
                    email: email.trim(),
                    lenguaje: lenguague,
                    rolUsuario: rol
                },
                () => history.push("/users/logIn"),
                errors => setBackendErrors(errors)
            );
        }
    }

    return(

        <Container>
            <div>
                <Link to="/usuarios/registrar"><FormattedMessage id={"back"}/></Link>
            </div>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)} />
            <br />
            <Container>
                <Form onSubmit={handleSubmit}>
                    <Form.Group>
                        <Form.Label><FormattedMessage id='user.SignUp.UserName'/></Form.Label>
                        <Form.Control
                            type="text" value={userName} onChange={e => setUserName(e.target.value)} required/>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label><FormattedMessage id='user.SignUp.Password'/></Form.Label>
                        <Form.Control type="password" value={password}
                                      onChange={e => setPassword(e.target.value)} required/>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label><FormattedMessage id='user.SignUp.RepeatPassword'/></Form.Label>
                        <Form.Control type="password" value={repeatPassword}
                                      onChange={e => setRepeatPassword(e.target.value)} required/>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label><FormattedMessage id='user.SignUp.Name'/></Form.Label>
                        <Form.Control
                            type="text" value={name} onChange={e => setName(e.target.value)} required/>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label><FormattedMessage id='user.SignUp.Surnames'/></Form.Label>
                        <Form.Control
                            type="text" value={surnames} onChange={e => setSurnames(e.target.value)} required/>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label><FormattedMessage id='user.SignUp.Dni'/></Form.Label>
                        <Form.Control
                            type="text" value={dni} onChange={e => setDni(e.target.value)} required/>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label><FormattedMessage id='user.SignUp.Email'/></Form.Label>
                        <Form.Control
                            type="text" value={email} onChange={e => setEmail(e.target.value)} required/>
                    </Form.Group>
                    <div className="input-group mb-3">
                        <LenguagueSelector id="lenguagueId" className="custom-select my-1 mr-sm-2"
                                           value={lenguague} onChange={e => setLenguague(e.target.value)} required />
                    </div>
                    <Button variant="primary" type="submit">
                        <FormattedMessage id={'user.SignUp.SignUp'}/>
                    </Button>
                </Form>
            </Container>

        </Container>
    );
}

export default RegistrarAdministrativo;
