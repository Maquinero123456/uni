document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("formulario").addEventListener('submit', validarFormulario);
});

function validarFormulario(evento) {
    evento.preventDefault();
    var usuario = document.getElementById('username').value;
    if(usuario.length < 3) {
        alert('El usuario es demasidao corto');
        return;
    }
    var clave = document.getElementById('password').value;
    if (clave.length < 3) {
        alert('La clave no es válida');
        return;
    }

    var clave2 = document.getElementById('password2').value;
    if (!clave.value===clave2.value) {
        alert('Las contraseñas no coinciden');
        return;
    }

    var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

    var email = document.getElementById('email').value;
    if (!email.match(validRegex)) {
        alert('El email no es valido');
        return;
    }

    var telefono = document.getElementById('telefono').value;
    if (telefono.length!=9) {
        alert('El telefono debe ser de longitud 9');
        return;
    }

    var nombre = document.getElementById('nombre').value;
    if(nombre.length < 3) {
        alert('El nombre es demsiado corto');
        return;
    }
    var apellido = document.getElementById('apellido').value;
    if (apellido.length < 3) {
        alert('El apellido es demasiado corto');
        return;
    }

    this.submit();
}