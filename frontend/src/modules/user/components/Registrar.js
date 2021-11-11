import {Container} from "react-bootstrap";
import {FormattedMessage} from "react-intl";
import React, {useState} from "react";
import {Link} from 'react-router-dom';
import RolSelector from "./RolSelector";
import {Redirect} from "react-router";

const Registrar = () => {


    const [rol, setRol] = useState("");


    if (rol === "") {
        return (
            <Container>
                <h3 className={"centeredParagraph"}>
                    <FormattedMessage id={"registrarUsuario"}/>
                </h3>
                <div>
                    <Link to="/"><FormattedMessage id={"back"}/></Link>
                </div>
                <br/>
                <div className="input-group mb-3">
                    <RolSelector id="rolId" className="custom-select my-1 mr-sm-2"
                                 value={rol} onChange={e => setRol(e.target.value)} required/>
                </div>
            </Container>

        )
    }

    if (rol === "0") {
        return <Redirect to={'/usuarios/registrar/adm'}/>
    }
    if (rol === "1") {
        return <Redirect to={'/usuarios/registrar/mec'}/>
    }
    if (rol === "2") {
        return <Redirect to={'/usuarios/registrar/cli'}/>
    }
}

export default Registrar;