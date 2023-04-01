function checkEmail() {
    var input = document.getElementsByName("email")[0];
    var button = document.getElementById("Registrati");
    var check = /^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    var esito;
    if (!input.value.match(check)) {
        $('#email').css("border-bottom", "1px solid #BB0000");
        $('#rsEmail').css("display", "inline");
        $('#rsEmail').css("color", "#BB0000").html('Email non valida');
        $('#email').css("color", "#BB0000");
        button.setAttribute("type", "button");
        esito = false;
    } else if (input.value.match(check)) {
        $('#email').css("border-bottom", "2px solid green");
        $('#rsEmail').css("display", "none");
        $('#email').css("color", "#1E1E24");
        esito = true;
    }
    return esito;
}

function checkPassword() {
    var input = document.getElementsByName("pwd")[0];
    var button = document.getElementById("Registrati");
    var check = /^[A-Za-z0-9.]{3,16}$/;
    var esito;
    if (!input.value.match(check)) {
        $('#pwd').css("border-bottom", "2px solid #BB0000");
        $('#rsPassword').css("display", "inline");
        $('#rsPassword').css("color", "#BB0000").html('Password non valida');
        $('#pwd').css("color", "#BB0000");
        button.setAttribute("type", "button");
        esito = false;
    } else if (input.value.match(check)) {
        $('#pwd').css("border-bottom", "2px solid green");
        $('#rsPassword').css("display", "none");
        $('#pwd').css("color", "#1E1E24");
        esito = true;
    }
    return esito;
}

function checkNome() {
    var input = document.getElementsByName("nome")[0];
    var button = document.getElementById("Registrati");
    var check = /^[A-Za-z]{2,32}$/;
    var esito;
    if (!input.value.match(check)) {
        $('#name').css("border-bottom", "2px solid #BB0000");
        $('#rsNome').css("display", "inline");
        $('#rsNome').css("color", "#BB0000").html('Nome non valido');
        $('#name').css("color", "#BB0000");
        button.setAttribute("type", "button");
        esito = false;
    } else if (input.value.match(check)) {
        $('#name').css("border-bottom", "2px solid green");
        $('#rsNome').css("display", "none");
        $('#name').css("color", "#1E1E24");
        esito = true;
    }
    return esito;
}

function checkCognome() {
    var input = document.getElementsByName("cognome")[0];
    var button = document.getElementById("Registrati");
    var check = /^[A-Za-z]{2,32}$/;
    var esito;
    if (!input.value.match(check)) {
        $('#surname').css("border-bottom", "2px solid #BB0000");
        $('#rsCognome').css("display", "inline");
        $('#rsCognome').css("color", "#BB0000").html('Cognome non valido');
        $('#surname').css("color", "#BB0000");
        button.setAttribute("type", "button");
        esito = false;
    } else if (input.value.match(check)) {
        $('#surname').css("border-bottom", "2px solid green");
        $('#rsCognome').css("display", "none");
        $('#surname').css("color", "#1E1E24");
        esito = true;
    }
    return esito;
}

function checkRegistrationSubmit() {
    var button = document.getElementById("Registrati");
    if (checkEmail() && checkPassword() && checkNome() && checkCognome()) {
        button.setAttribute("type", "submit");
    }
}
