<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <p>Inicia sesion para acceder a la web:</p>
    <!-- Formulario para iniciar sesion -->
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="username">Usuario: </label><br><br>
        <input type="text" id="username" name="username" required><br><br>
        <label for="password">Contraseña: </label><br><br>
        <input type="password" id="password" name="password" required><br><br>
        <input type="submit" value="Iniciar sesion"><br>
    </form>
    <!-- Si el servlet devuelve error, lo mostramos -->
    <%
        int error = 0;
        try{
            error = Integer.parseInt(request.getAttribute("error").toString());
        }catch(NumberFormatException e){

        }catch (NullPointerException e){}
        switch(error){
            case 1:%>
            <p style="color:red">Nombre de usuario o password incorrecto</p>
            <%break;
                case 2:%>
            <p style="color:red">Nombre de usuario o password demasiado corto</p>
            <%break;
        default:}%>
    <hr>
    <!-- Enlace para registrarse -->
    <a href="${pageContext.request.contextPath}/register">Si no tienes cuenta registrate</a><br><br>
    <!-- Enlace para recuperar contraseña -->
    <a href="${pageContext.request.contextPath}/recuperarPassword">Recuperar password</a><br><br>
    <!-- Enlace para acceder a la pagina sin iniciar sesion -->
    <a href="index.jsp?pag=1&titulo=&autor=">Accede sin cuenta</a>
</body>
</html>