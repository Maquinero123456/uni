document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("formulario").addEventListener('submit', validarFormulario);
});

function validarFormulario(evento) {
    evento.preventDefault();
    var usuario = document.getElementById('username').value;
    if(usuario.length < 3) {
        alert('No has escrito nada en el o es demasiado corto');
        return;
    }
    var clave = document.getElementById('password').value;
    if (clave.length < 3) {
        alert('La clave no es válida');
        return;
    }
    this.submit();
}

function loginDoc(){
    window.location.assign(window.location.hostname);
}

function registroDoc(){
    window.location.assign(window.location.hostname+"/register");
}

function passwordDoc(){
    window.location.assign(window.location.hostname+"/recuperarPassword");
}