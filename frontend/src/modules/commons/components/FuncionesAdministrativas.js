import { Container, Jumbotron, Button } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import { useSelector } from "react-redux";
import * as userSelectors from "../../user/selectors";
import { useHistory } from "react-router";

const FuncionesAdministrativas = () => {

    const userRole = useSelector(userSelectors.getUserRole);
    const history = useHistory();

    return (

        <div>
            {
                // The user must be logged in and be an administrative personal
                userRole === "ADMINISTRATIVO" ?
                    <Jumbotron fluid className="bg-light border border-ssecondary">
                        <Container>
                            <div className="d-flex justify-content-center">
                                <h5>
                                    <FormattedMessage id="paginaPrincipal.administrativos.tareasAdministrativas" />
                                </h5>
                            </div>
                            <br />
                            <div className="centeredHorizontalDiv">
                                <div className="d-flex justify-content-center">
                                    <form onSubmit={() => history.push('/users/signUp')}>
                                        <Button type="submit" className="d-flex justify-content-center" variant="info">
                                            <FormattedMessage id="app.RedirectHome.SignIn" />
                                        </Button>
                                    </form>
                                </div>
                                &nbsp;&nbsp;
                                <div className="d-flex justify-content-center">
                                    <form onSubmit={() => history.push('/vehicle/newVehicle')}>
                                        <Button type="submit" className="d-flex justify-content-center" variant="info">
                                            <FormattedMessage id="vehiculo.registroVehiculo" />
                                        </Button>
                                    </form>
                                </div>
                            </div>

                        </Container>
                    </Jumbotron>
                    :
                    null

            }
        </div>

    );
}

export default FuncionesAdministrativas;