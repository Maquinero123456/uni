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
<div id="card">
    <div id="card-content">
        <div id="card-title">
            <h2>RECUPERAR CONTRASEÑA</h2>
            <div class="underline-title"></div>
        </div>
    <form action="${pageContext.request.contextPath}/recuperarPassword" method="post" class="form">
        <label for="username">Usuario:</label>
        <input type="text" name="username" id="username" class="form-content" required>
        <div class="form-border"></div>
        <input id="submit-btn" type="submit" name="submit" value="SUBMIT" />
    </form>
    <%
        if(request.getAttribute("password")!=null){%>
            <form action="${pageContext.request.contextPath}/recuperarPassword" method="post" class="form">
                <input type="text" name="password" id="password" class="response-content" value=<%=request.getAttribute("password")%> readonly>
                <div class="form-border"></div>
            </form>
        <%}else if(request.getAttribute("error")!=null){%>
            <p style="color:red">El usuario no existe</p>
        <%}%>
    <hr>
        <div id="links">
            <button class="boton" id="boton1" name="boton1"  onclick="window.location.href='${pageContext.request.contextPath}'">Si tienes cuenta, haz login aqui</button><br><br>
            <button class="boton" id="boton3" name="boton3"  onclick="window.location.href='${pageContext.request.contextPath}/register'">Si no tienes cuenta registrate</button><br><br>
            <button class="boton" id="boton2" name="boton2" onclick="window.location.href='${pageContext.request.contextPath}/index.jsp?pag=1&titulo=&autor='">Accede sin cuenta</button>
        </div>
    </div></div>
    <footer class="autor">
        <p>David Navarro Jimena (Universidad de Malaga)</p>
        <p>Copyright 2023 Todos los derechos reservados</p>
    </footer>
</body>
</html>
