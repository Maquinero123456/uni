<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recuperar Contraseña</title>
</head>
<body>
    <!-- Formulario para buscar contraseña -->
    <form action="${pageContext.request.contextPath}/recuperarPassword" method="post">
        <label for="username">Usuario:</label>
        <input type="text" name="username" id="username">
        <input type="submit">
    </form>
    <!-- Si el servlet devuelve la contraseña la mostramos
         Si el servlet devulve error, lo mostramos-->
    <%
        if(request.getAttribute("password")!=null){%>
            <p>Contraseña: <%=request.getAttribute("password")%></p>
        <%}else if(request.getAttribute("error")!=null){%>
            <p style="color:red">El usuario no existe</p>
        <%}%>
    <hr>
    <!-- Enlace para iniciar sesion -->
    <a href="${pageContext.request.contextPath}">Si tienes cuenta, haz login aqui</a><br><br>
    <!-- Enlace para registrarse -->
    <a href="${pageContext.request.contextPath}/register">Si no tienes cuenta registrate</a><br><br>
    <!-- Enlace para acceder sin iniciar sesion -->
    <a href="index.jsp?pag=1&titulo=&autor=">Accede sin cuenta</a>
</body>
</html>
