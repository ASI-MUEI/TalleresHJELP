import { Container, Button } from "react-bootstrap";
import { FormattedMessage, useIntl } from "react-intl";
import { useState } from "react";
import backend from "../../../backend";
import { useHistory } from 'react-router-dom';
import Errors from "../../commons/components/Errors";
import LenguagueSelector from "./LenguagueSelector";

const SignUp = () => {

    const history = useHistory();
    const intl = useIntl();
    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");
    const [repeatPassword, setRepeatPassword] = useState("");
    const [name, setName] = useState("");
    const [surnames, setSurnames] = useState("");
    const [email, setEmail] = useState("");
    const [lenguague, setLenguague] = useState("");
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
                    lenguaje: lenguague
                },
                () => history.push("/users/logIn"),
                errors => setBackendErrors(errors)
            );
        }
    }

    return (

        <Container>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)} />
            <Container className="signUpLogInDivFirst bg-light border border-secondary ">
                <br />
                <div className="d-flex justify-content-center">
                    <h3>Talleres HJELP</h3>
                </div>
            </Container>
            <br />
            <Container className="signUpDivSecond bg-light border border-secondary ">
                <form onSubmit={e => handleSubmit(e)}>
                    <br />
                    <div className="d-flex justify-content-center">
                        <h6><FormattedMessage id='user.SignUp.Welcome' /></h6>
                    </div>
                    <br />

                    <div className="input-group mb-3">
                        <div className="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1">@</span>
                        </div>
                        <FormattedMessage id='user.SignUp.UserName'>
                            {placeholder => <input placeholder={placeholder} className="form-control" value={userName} onChange={e => setUserName(e.target.value)} required />}
                        </FormattedMessage>

                    </div>

                    <div className="input-group mb-3">
                        <FormattedMessage id='user.SignUp.Password'>
                            {placeholder => <input placeholder={placeholder} className="form-control" type="password" value={password} onChange={e => setPassword(e.target.value)} required />}
                        </FormattedMessage>
                    </div>

                    <div className="input-group mb-3">
                        <FormattedMessage id='user.SignUp.RepeatPassword'>
                            {placeholder => <input placeholder={placeholder} className="form-control" type="password" value={repeatPassword} onChange={e => setRepeatPassword(e.target.value)} required />}
                        </FormattedMessage>
                    </div>

                    <div className="input-group mb-3">
                        <FormattedMessage id='user.SignUp.Name'>
                            {placeholder => <input placeholder={placeholder} className="form-control" value={name} onChange={e => setName(e.target.value)} required />}
                        </FormattedMessage>
                    </div>

                    <div className="input-group mb-3">
                        <FormattedMessage id='user.SignUp.Surnames'>
                            {placeholder => <input placeholder={placeholder} className="form-control" value={surnames} onChange={e => setSurnames(e.target.value)} required />}
                        </FormattedMessage>
                    </div>

                    <div className="input-group mb-3">
                        <FormattedMessage id='user.SignUp.Email'>
                            {placeholder => <input placeholder={placeholder} className="form-control" type="email" value={email} onChange={e => setEmail(e.target.value)} required />}
                        </FormattedMessage>
                    </div>
                    <div className="input-group mb-3">
                        <LenguagueSelector id="lenguagueId" className="custom-select my-1 mr-sm-2"
                            value={lenguague} onChange={e => setLenguague(e.target.value)} required />
                    </div>
                    <br />
                    <div className="d-flex justify-content-center">
                        <Button type="submit" className="d-flex justify-content-center" variant="success">
                            <FormattedMessage id='user.SignUp.SignUp' />
                        </Button>
                    </div>

                </form>
            </Container>

        </Container>


    );
}

export default SignUp;