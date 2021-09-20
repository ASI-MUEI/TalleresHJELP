import { Container } from "react-bootstrap";
import commonsExportObj from "../../commons";
import { useSelector } from "react-redux";
import * as userSelectors from '../../user/selectors';
import MyProfileButtons from "./MyProfileButtons";


const UserProfileDetails = ({ userData }) => {

    const userName = useSelector(userSelectors.getUserName);

    const userPhoto = (databasePhoto) => {
        if (databasePhoto === null || databasePhoto === undefined) {
            return commonsExportObj.constants.IMG_PROFILE_DEFAULT;
        }

        return databasePhoto;
    }

    return (
        <Container>
            <div className="d-flex justify-content-center">
                <img className="profileImg" alt="profile" src={`data:image/jpeg;base64, ${userPhoto(userData.fotoPerfil)}`} />
            </div>
            <br />
            <div className="d-flex justify-content-center">
                <h3>{`${userData.nombrePilaUsuario} ${userData.apellidosUsuario}`}</h3>
                &nbsp;
                &nbsp;
                &nbsp;
            </div>
            <br />
            <div className="d-flex justify-content-center">
                <h5>{`@${userData.nombreUsuario}`}</h5>
            </div>
            <br />
            {
                userName === userData.nombreUsuario ?

                    <div>
                        <br />
                        <MyProfileButtons userName={userData.nombreUsuario} />
                    </div>

                    :

                    null
            }

            <br />
        </Container>
    )
}

export default UserProfileDetails;