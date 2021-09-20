import { Container, Jumbotron } from "react-bootstrap";
import { useSelector } from "react-redux";
import * as userSelectors from "../../user/selectors";
import SessionButtons from "./SessionButtons";


const RedirectHome = () => {

    const userName = useSelector(userSelectors.getUserName);

    return (

        <div>
            {
                // If the user is logged in there is no need to show him/her the session buttons
                userName ?
                    null
                    :
                    <Jumbotron fluid className="bg-light border border-ssecondary">
                        <Container>
                            {
                                <SessionButtons />
                            }
                        </Container>
                    </Jumbotron>
            }
        </div>

    );
}

export default RedirectHome;