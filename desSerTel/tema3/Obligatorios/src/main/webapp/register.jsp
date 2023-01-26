<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro</title>
    <link rel="stylesheet" href="register.css">
</head>
<body>
<script type="text/javascript" src="register.js"></script>
    <div id="card">
        <div id="card-content">
            <div id="card-title">
                <h2>REGISTRO</h2>
                <div class="underline-title"></div>
            </div>
            <form action="${pageContext.request.contextPath}/register" method="post" class="form" id="formulario">
                <label for="username" style="padding-top:13px">Usuario </label>
                <input type="text" id="username" name="username" class="form-content" required>
                <div class="form-border"></div>
                <label for="password">Contraseña </label>
                <input type="password" id="password" name="password" class="form-content" required>
                <div class="form-border"></div>
                <label for="password2">Repite contraseña </label>
                <input type="password" id="password2" name="password2" class="form-content" required>
                <div class="form-border"></div>
                <label for="email">Email </label>
                <input type="text" id="email" name="email" class="form-content" required>
                <div class="form-border"></div>
                <label for="telefono">Telefono </label>
                <input type="number" id="telefono" name="telefono" class="form-content" required>
                <div class="form-border"></div>
                <label for="nombre">Nombre </label>
                <input type="text" id="nombre" name="nombre" class="form-content" required>
                <div class="form-border"></div>
                <label for="apellido">Apellido </label>
                <input type="text" id="apellido" name="apellido" class="form-content" required>
                <div class="form-border"></div>
                <a href="${pageContext.request.contextPath}/recuperarPassword"><legend id="forgot-pass">Recuperar contraseña</legend>
                </a>
                <input id="submit-btn" type="submit" name="enviar" id="enviar" value="SUBMIT" />
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
            <div id="links">
                <button class="boton" id="boton1" name="boton1"  onclick="window.location.href='${pageContext.request.contextPath}'">Si tienes cuenta, haz login aqui</button><br><br>
                <button class="boton" id="boton2" name="boton2" onclick="window.location.href='${pageContext.request.contextPath}/index.jsp?pag=1&titulo=&autor='">Accede sin cuenta</button>
            </div>
        </div>
    </div>
    <footer class="autor">
        <p>David Navarro Jimena (Universidad de Malaga)</p>
        <p>Copyright 2023 Todos los derechos reservados</p>
    </footer>
</body>
</html>
