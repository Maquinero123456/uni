<%@ page import="com.example.parte2.controlLibros" %>
<%@ page import="com.example.parte2.Libros" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Index</title>
    </head>
    <body>
        <!-- Comprobamos si esta la sesion iniciada, mostramos el usuario -->
        <%if((String)session.getAttribute("loginName")!=null){%>
        <pre>User: ${loginName}        <a href="${pageContext.request.contextPath}/logout">Logout</a></pre>
        <hr>
        <%}%>
        <!-- Formulario para buscar libros -->
        <form action="index.jsp?pag=1">
            <label for="titulo">Titulo: </label>
            <input type="text" id="titulo" name="titulo">
            <label for="autor">Autor: </label>
            <input type="text" id="autor" name="autor">
            <input type="submit" value="Buscar">
        </form>
        <hr>
        <!-- Tabla para mostrar los libros -->
        <table style="text-align: center">
            <!-- Headers de la tabla -->
            <tr>
                <th>Titulo</th>
                <th>Autor</th>
                <th>Enlace</th>
                <th>Resumen</th>
                <th>Descargas</th>
            </tr>

                <!-- Datos de la tabla -->
                <%
                    //Cargamos el numero de pagina que estamos mostrando, como default sera el 1
                    int pag = 1;
                    //En caso de que haya un parametro con numero de pagina, usamos ese
                    try{
                        pag = Integer.parseInt(request.getParameter("pag"));
                    }catch(NumberFormatException e){}

                    //Cargamos autor y titulo desde parametros
                    String autor = request.getParameter("autor");
                    String titulo = request.getParameter("titulo");
                    //Con estos ultimos, buscamos los libros
                    ArrayList<Libros> libros = controlLibros.busquedaLibrosjson(titulo, autor);
                    //Creamos el rango para mostrar libros
                    int limit = 0;
                    if(libros.size()>pag*5){
                        limit=pag*5;
                    }else{
                        limit=libros.size();
                    }
                    //Iteramos sobre los libros
                    for(int i = pag*5-5; i<limit; i++){
                        Libros libro = libros.get(i);
                %><!-- Creamos una fila con la informacion de los libros -->
                    <tr>
                    <td><%=libro.getTitulo()%></td>
                    <td><%=libro.getAutor()%></td>
                    <!-- Si no hay sesion iniciada, no mostramos la url de descarga, mostramos un enlace a la pagina de inicio de sesion -->
                    <%if((String)session.getAttribute("loginName")==null){%>
                    <td><a href="${pageContext.request.contextPath}">Inicia sesion para poder descargar</a></td>
                    <%}else{%>
                    <!-- Mostramos el enlace de descarga como un boton de submit para un form invisible
                         Cuando se pulsa, enviamos al servlet de descargar pdf el enlace, titulo y autor del libro
                         El enlace para descargar el libro
                         El autor y titulo para actualizar el numero de descargas-->
                    <td><form action="${pageContext.request.contextPath}/descargarPdf" method="get">
                        <input type="hidden" name="enlace" id="enlace" value="<%=libro.getEnlace()%>">
                        <input type="hidden" name="titulo" id="titulo2" value="<%=libro.getTitulo()%>">
                        <input type="hidden" name="autor" id="autor2" value="<%=libro.getAutor()%>">
                        <input type="submit" name="boton" id="boton"
                               value=<%=libro.getEnlace().split("\\\\")[libro.getEnlace().split("\\\\").length-1]%>>
                    </form></td>
                    <%}%>
                    <td><%=libro.getResumen()%></td>
                    <td><%=libro.getDescargas()%></td>
        </tr>
                    <%}
                    %>

        </table>
        <hr>
        <!-- Mostramos el numero de paginas como enlaces si hay mas de una disponible -->
        <%for(int j = 1; j<=controlLibros.busquedaLibrosjson(titulo, autor).size()/5; j++){
            if(pag==j){%>
            <span><%=pag%></span>
            <%}else{%>
            <a href="index.jsp?pag=<%=j%>&titulo=<%=titulo%>&autor=<%=autor%>"><%=j%></a>
        <%}}
        %>
        <%if(controlLibros.busquedaLibrosjson(titulo, autor).size()/5>1){%>
        <hr>
        <%}%>
    </body>
</html>