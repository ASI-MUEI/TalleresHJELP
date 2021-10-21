import { Form, Button, Container } from "react-bootstrap"
import Multiselect from "multiselect-react-dropdown"
import { useIntl } from "react-intl"

const NewVehicle = () => {

    const intl = useIntl()

    return (
        <Container>
            <h4 className="centeredParagraph">
                {intl.formatMessage({ id: 'vehiculo.registroVehiculo' })}
            </h4>
            <br/>
            <Multiselect
                placeholder={intl.formatMessage({ id: 'vehiculo.registroVehiculo.usuario' })}
                isObject={false}
                selectedValues={[]}
                showArrow="true" />
            <br
            />
            <Form>
                <Form.Group controlId="formBasicEmail">
                    <Form.Control type="email" placeholder={intl.formatMessage({ id: 'vehiculo.registroVehiculo.numBastidor' })} />
                    <Form.Text className="text-muted">
                    </Form.Text>
                </Form.Group>
                <Form.Group controlId="formBasicEmail">
                    <Form.Control type="email" placeholder={intl.formatMessage({ id: 'vehiculo.registroVehiculo.matricula' })} />
                    <Form.Text className="text-muted">
                    </Form.Text>
                </Form.Group>
                <Multiselect
                    placeholder={intl.formatMessage({ id: 'vehiculo.registroVehiculo.marca' })}
                    isObject={false}
                    selectedValues={[]}
                    showArrow="true" />
                <br
                />
                <Multiselect
                    placeholder={intl.formatMessage({ id: 'vehiculo.registroVehiculo.modelo' })}
                    isObject={false}
                    selectedValues={[]}
                    showArrow="true" />
                <br
                />
                <Multiselect
                    placeholder={intl.formatMessage({ id: 'vehiculo.registroVehiculo.flota' })}
                    isObject={false}
                    selectedValues={[]}
                    showArrow="true" />
                <br
                />
                <Button className="center" variant="success" type="submit">
                    {intl.formatMessage({ id: 'app.Commons.Save' })}
                </Button>
            </Form>
        </Container>
    )

}

export default NewVehicle