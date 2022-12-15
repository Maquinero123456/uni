<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro</title>
    <link rel="stylesheet" href="register.css">
</head>
<body>
    <p>Introduce los datos para registrarte:</p>
    <form action="${pageContext.request.contextPath}/register" method="post">
        <label for="username">Usuario: </label><br><br>
        <input type="text" id="username" name="username" required><br><br>
        <label for="password">Contraseña: </label><br><br>
        <input type="password" id="password" name="password" required><br><br>
        <input type="submit" value="Registrarse"><br>
    </form>
    <%
        int error = 0;
        try{
            error = Integer.parseInt(request.getAttribute("error").toString());
        }catch(NumberFormatException e){

        }catch (NullPointerException e){}
        switch(error){
            case 1:%>
                <p style="color:red">Ya existe un usuario con ese nombre</p>
                <%break;
            case 2:%>
                <p style="color:red">Nombre de usuario o password demasiado corto</p>
                <%break;
            default:}%>
    <hr><br>
    <a href="${pageContext.request.contextPath}">Si tienes cuenta, haz login aqui</a><br><br>
    <a href="${pageContext.request.contextPath}/recuperarPassword">Recuperar password</a><br><br>
    <a href="index.jsp?pag=1&titulo=&autor=">Accede sin cuenta</a>
</body>
</html>
