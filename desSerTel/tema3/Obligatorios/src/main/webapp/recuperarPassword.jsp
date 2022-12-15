<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 10/12/2022
  Time: 6:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recuperar Contraseña</title>
    <link rel="stylesheet" href="recuperarPassword.css">
</head>
<body>
    <form action="${pageContext.request.contextPath}/recuperarPassword" method="post">
        <label for="username">Usuario:</label>
        <input type="text" name="username" id="username">
        <input type="submit">
    </form>
    <%
        if(request.getAttribute("password")!=null){%>
            <p>Contraseña: <%=request.getAttribute("password")%></p>
        <%}else if(request.getAttribute("error")!=null){%>
            <p style="color:red">El usuario no existe</p>
        <%}%>
    <hr>
    <a href="${pageContext.request.contextPath}">Si tienes cuenta, haz login aqui</a><br><br>
    <a href="${pageContext.request.contextPath}/register">Si no tienes cuenta registrate</a><br><br>
    <a href="index.jsp?pag=1&titulo=&autor=">Accede sin cuenta</a>
</body>
</html>
