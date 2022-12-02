<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <p>Inicia sesion para acceder a la web:</p><br><br>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="username">Usuario: </label><br><br>
        <input type="text" id="username" name="username" required><br><br>
        <label for="password">Contraseña: </label><br><br>
        <input type="password" id="password" name="password" required><br><br>
        <input type="submit" value="Submit"><br><br>
    </form>
    <a href="${pageContext.request.contextPath}/register">Si no tienes cuenta registrate</a>
</body>
</html>