import { Container, Form, Button } from "react-bootstrap";
import { useIntl } from "react-intl";
import { Multiselect } from 'multiselect-react-dropdown';

const NuevaReparacion = () => {

    const intl = useIntl();
    {/**
        const [listaClientes, setListaClientes] = useState([]);
    const [listaCoches, setListaCoches] = useState([]);
     */}
    

    

    return(
        <Container>
            <br/>
            <h2 className="centeredParagraph">{intl.formatMessage({ id: 'paginaHorario.nuevaReparacion.nuevaReparacion' })}</h2>
            <br/>
            <Form>
                <Multiselect
                    placeholder={intl.formatMessage({ id: 'paginaHorario.nuevaReparacion.selectorCliente' })}
                    isObject={false}
                    selectedValues={[]}
                    showArrow="true" />
                    <br 
                />
                </Form>
                <Form>
                <Multiselect
                    placeholder={intl.formatMessage({ id: 'paginaHorario.nuevaReparacion.selectorVehiculo' })}
                    isObject={false}
                    selectedValues={[]}
                    showArrow="true" />
                    <br 
                />
                <Multiselect
                    placeholder={intl.formatMessage({ id: 'paginaHorario.nuevaReparacion.selectorElevador' })}
                    isObject={false}
                    selectedValues={[]}
                    showArrow="true" />
                    <br 
                />
                <Multiselect
                    placeholder={intl.formatMessage({ id: 'paginaHorario.nuevaReparacion.selectorHorario' })}
                    isObject={false}
                    selectedValues={[]}
                    showArrow="true" />
                    <br 
                />
                </Form>
                <div className="d-flex justify-content-center">
                    <Button variant="success" type="submit">
                        {intl.formatMessage({ id: 'app.Commons.Save' })}
                    </Button>
                </div>
                <br/><br/><br/><br/>
        </Container>
        

    )

}

export default NuevaReparacion;