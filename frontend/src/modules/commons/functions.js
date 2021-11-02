const tipoLabelConcurso = (estado) => {

    switch(estado){
        case "EN_PREPARACION" : return "info"
        case "ABIERTO" : return "success"
        case "VOTACION": return "warning"
        case "FINALIZADO" : return "secondary"
        default : return "success"
    }
}

const tipoLabelTrabajo = (estado) => {

    switch(estado){
        case "ABIERTO" : return "warning"
        case "CERRADO" : return "success"
        default : return "success"
    }
}

const tipoLabelVotante = (estado) => {

    switch(estado){
        case "CUALQUIERA" : return "success"
        case "JURADO" : return "warning"
        case "PARTICIPANTE" : return "info"
        default : return "success"
    }
}


const fileToBase64 = (file, funcion) => {

    const reader = new FileReader();

    if (file) {
        reader.readAsDataURL(file);
        reader.onloadend = function () {
            let imgB64 = reader.result;
            let indexOfComma = imgB64.indexOf(",");
            imgB64 = imgB64.substr(indexOfComma + 1);
            funcion(imgB64);
          }
    }
}

const calculateDiffBetweenDates = (date2, date1) => {

    let diff = date2 - date1;
    if (diff > 0) {
        return diff;
    } else {
        return 0;
    }
}

let exportObj = {tipoLabelTrabajo, tipoLabelVotante, fileToBase64, calculateDiffBetweenDates}

export default exportObj;