import { useEffect, useState } from "react";
import { Button, Form } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import { useSelector, useDispatch } from "react-redux";
import { useHistory } from "react-router";
import * as userSelectors from "../selectors";
import * as userActions from "../actions";

const ChangueProfileData = () => {

    const history = useHistory();
    const dispatch = useDispatch();

    const userData = useSelector(userSelectors.getUserData);

    const [nombrePilaUsuario, setNombrePilaUsuario] = useState("");
    const [apellidosUsuario, setApellidosUsuario] = useState("");
    const [email, setEmail] = useState("");

    useEffect(() => {
        setNombrePilaUsuario(userData.nombrePilaUsuario);
        setApellidosUsuario(userData.apellidosUsuario);
        setEmail(userData.email);
        // eslint-disable-next-line
    }, []);

    const handleSubmit = (event) => {
        event.preventDefault();
        dispatch(userActions.updateUser(
            {
                nombreUsuario: userData.nombreUsuario,
                nombrePilaUsuario,
                email,
                apellidosUsuario,
            },
            () => { history.push(`users/${userData.nombreUsuario}`); window.localStorage.clear() },
            () => history.push(`users/${userData.nombreUsuario}/changue-data`),
        ));
    }

    return (
        <Form>
            <Form.Group className="mb-3" controlId="nombreUsuario">
                <Form.Label><FormattedMessage id='user.ChangueData.Name' /></Form.Label>
                <Form.Control value={nombrePilaUsuario} maxlength="50" onChange={e => setNombrePilaUsuario(e.target.value)} />
            </Form.Group>
            <Form.Group className="mb-3" controlId="apellidos">
                <Form.Label><FormattedMessage id='user.ChangueData.Surnames' /></Form.Label>
                <Form.Control value={apellidosUsuario} maxlength="100" onChange={e => setApellidosUsuario(e.target.value)} />
            </Form.Group>
            <Form.Group className="mb-3" controlId="Email">
                <Form.Label><FormattedMessage id='user.ChangueData.Email' /></Form.Label>
                <Form.Control type="emial" value={email} maxlength="200" onChange={e => setEmail(e.target.value)} />
            </Form.Group>
            <Button variant="primary" type="submit" onClick={e => handleSubmit(e)}>
                <FormattedMessage id="app.Commons.Save" />
            </Button>
        </Form>
    );
}

export default ChangueProfileData;