<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="login.css">
</head>
<body>
    <div id="card">
        <div id="card-content">
            <div id="card-title">
                <h2>LOGIN</h2>
                <div class="underline-title"></div>
            </div>
                <form action="${pageContext.request.contextPath}/login" method="post" class="form">
                    <label for="username" style="padding-top:13px">Usuario </label>
                    <input type="text" id="username" name="username" class="form-content" required>
                    <div class="form-border"></div>
                    <label for="password">Contraseña </label>
                    <input type="password" id="password" name="password" class="form-content2" required>
                    <div class="form-border2"></div>
                    <a href="${pageContext.request.contextPath}/recuperarPassword"><legend id="forgot-pass">Recuperar contraseña</legend>
                    </a>
                    <input id="submit-btn" type="submit" name="submit" value="LOGIN" />
                </form>
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
                <div id="links">
                    <a href="${pageContext.request.contextPath}/register">Si no tienes cuenta registrate</a><br><br>
                    <a href="index.jsp?pag=1&titulo=&autor=">Accede sin cuenta</a>
                </div>


        </div>
    </div>

</body>
</html>