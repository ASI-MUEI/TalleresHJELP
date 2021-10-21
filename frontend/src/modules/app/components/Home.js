import React from 'react';
import { Container } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import {useSelector} from 'react-redux';
import * as userSelectors from '../../user/selectors';
import RedirectHome from "./RedirectHome";
import FuncionesAdministrativas from '../../commons/components/FuncionesAdministrativas';


const Home = () => {

    const userName = useSelector(userSelectors.getName);

    return (
        <Container>
            <br/>
            {
                userName ?
                    <div className="d-flex justify-content-center">
                        <h4>
                            <FormattedMessage id="app.Home.WelcomeBack" />{`, ${userName}`}
                        </h4>
                    </div>
                    :

                    <div className="d-flex justify-content-center">
                        <br/>
                        <h4>
                            <FormattedMessage id="app.Home.Welcome" />
                        </h4>
                    </div>                    
            }
            <br/>
            <FuncionesAdministrativas/>

            <br/>
            <RedirectHome />
            <br />
            <br />
        </Container>
    );
}

export default Home;