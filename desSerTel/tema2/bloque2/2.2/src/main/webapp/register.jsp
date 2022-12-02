<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro</title>
</head>
<body>
    <p>Introduce los datos para registrarte:</p><br><br>
    <form action="${pageContext.request.contextPath}/register" method="post">
        <label for="username">Usuario: </label><br><br>
        <input type="text" id="username" name="username" required><br><br>
        <label for="password">Contraseña: </label><br><br>
        <input type="password" id="password" name="password" required><br><br>
        <input type="submit" value="Submit"><br><br>
    </form>
    <a href="${pageContext.request.contextPath}/MainPage">Si tienes cuenta, haz login aqui</a>
</body>
</html>
