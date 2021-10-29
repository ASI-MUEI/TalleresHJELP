import { FormattedMessage } from "react-intl";

const RolSelector = (selectProps) => {

    const lenguagues = [
        {id: 0, value: "Administrativo"},
        {id: 1, value: "Mecánico"},
        {id: 2, value: "Cliente"},
    ]
    
    return (

        <select {...selectProps}>

            <FormattedMessage id='user.SignUp.Rol'>
                {message => (<option value="">{message}</option>)}
            </FormattedMessage>

            {lenguagues.map(lenguaje => 
                <option key={lenguaje.id} value={lenguaje.id}>{lenguaje.value}</option>
            )}

        </select>

    );

}

export default RolSelector;