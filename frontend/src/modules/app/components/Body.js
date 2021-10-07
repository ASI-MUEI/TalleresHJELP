import React from 'react';
import { Route, Switch } from 'react-router-dom';
import { Container } from 'react-bootstrap';
import FindUsers from '../../user/components/FindUsers';
import User from '../../user/components/User';
import Home from './Home';
import SignUp from '../../user/components/SignUp';
import LogIn from '../../user/components/LogIn';
import ChanguePassword from '../../user/components/ChanguePassword';
import ChangueProfileData from '../../user/components/ChangueProfileData';
import RecoverUser from '../../user/components/RecoverUser';
import ResetPassword from '../../user/components/ResetPassword';
import PaginaHorario from '../../horario/components/PaginaHorario';
import Reparacion from '../../reparacion/components/Reparacion';

const Body = () => {

    return (

        <Container>
            <br />
            <Switch>
                <Route exact path="/"><Home /></Route>
                <Route exact path="/users/find-users"><FindUsers /></Route>
                <Route exact path="/users/signUp"><SignUp /></Route>
                <Route exact path="/users/login"><LogIn /></Route>
                <Route exact path="/users/recover"><RecoverUser /></Route>
                <Route exact path="/users/:userName"><User /></Route>
                <Route exact path="/users/:userName/changue-password"><ChanguePassword /></Route>
                <Route exact path="/users/:userName/reset-password/:token"><ResetPassword /></Route>
                <Route exact path="/users/:userName/changue-data"><ChangueProfileData /></Route>
                <Route exact path="/horario"><PaginaHorario/></Route>
                <Route exact path="/reparacion"><Reparacion /></Route>
                <Route><Home /></Route>
            </Switch>
        </Container>

    );

};

export default Body;
