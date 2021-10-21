import { FormattedMessage } from "react-intl";
import { Button } from "react-bootstrap";
import { useHistory } from "react-router";

const SessionButtons = () => {

    const history = useHistory();

    return (
        <div>
            <div className="d-flex justify-content-center">
                <h5>
                    <FormattedMessage id="app.RedirectHome.paragraphAccount" />
                </h5>
            </div>
            <br />
            <div className="d-flex justify-content-center">
                <form onSubmit={() => history.push('/users/login')}>
                    <Button type="submit" variant="info">
                        <FormattedMessage id="app.RedirectHome.LogIn" />
                    </Button>
                </form>
                &emsp;
            </div>
        </div>
    )
}

export default SessionButtons;