<%@ page import="com.example.parte2.controlLibros" %>
<%@ page import="com.example.parte2.Libros" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Index</title>
        <link rel="stylesheet" href="index.css">
    </head>
    <body>
        <%if((String)session.getAttribute("loginName")!=null){%>
        <pre>User: ${loginName}        <a href="${pageContext.request.contextPath}/logout">Logout</a></pre>
        <hr>
        <%}%>
        <form action="index.jsp?pag=1">
            <label for="titulo">Titulo: </label>
            <input type="text" id="titulo" name="titulo">
            <label for="autor">Autor: </label>
            <input type="text" id="autor" name="autor">
            <input type="submit" value="Buscar">
        </form>
        <hr>
        <table style="text-align: center">
            <tr>
                <th>Titulo</th>
                <th>Autor</th>
                <th>Enlace</th>
                <th>Resumen</th>
                <th>Descargas</th>
            </tr>
                

                    <%
                        int pag = 1;
                        try{
                            pag = Integer.parseInt(request.getParameter("pag"));
                        }catch(NumberFormatException e){}

                        String autor = request.getParameter("autor");
                        String titulo = request.getParameter("titulo");
                        ArrayList<Libros> libros = controlLibros.busquedaLibrosjson(titulo, autor);
                        int limit = 0;
                        if(libros.size()>pag*5){
                            limit=pag*5;
                        }else{
                            limit=libros.size();
                        }
                    for(int i = pag*5-5; i<limit; i++){
                        Libros libro = libros.get(i);
                %><tr>
                    <td><%=libro.getTitulo()%></td>
                    <td><%=libro.getAutor()%></td>
                    <%if((String)session.getAttribute("loginName")==null){%>
                    <td><a href="${pageContext.request.contextPath}">Inicia sesion para poder descargar</a></td>
                    <%}else{%>
                    <td><form action="${pageContext.request.contextPath}/descargarPdf" method="get">
                        <input type="hidden" name="enlace" id="enlace" value="<%=libro.getEnlace()%>">
                        <input type="hidden" name="titulo" id="titulo2" value="<%=libro.getTitulo()%>">
                        <input type="hidden" name="autor" id="autor2" value="<%=libro.getAutor()%>">
                        <input type="submit" name="boton" id="boton" value=<%=libro.getEnlace().split("\\\\")[libro.getEnlace().split("\\\\").length-1]%>>
                    </form></td>
                    <%}%>
                    <td><%=libro.getResumen()%></td>
                    <td><%=libro.getDescargas()%></td>
        </tr>
                    <%}
                    %>

        </table>
        <hr>
        <%for(int j = 1; j<=controlLibros.busquedaLibrosjson(titulo, autor).size()/5; j++){
            if(pag==j){%>
            <span><%=pag%></span>
            <%}else{%>
            <a href="index.jsp?pag=<%=j%>&titulo=<%=titulo%>&autor=<%=autor%>"><%=j%></a>
        <%}}
        %>
        <hr>
    </body>
</html>