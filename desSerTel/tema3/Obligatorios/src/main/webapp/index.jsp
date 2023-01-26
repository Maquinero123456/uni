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
        <script  type="text/javascript" src="index.js"></script>
        <header>
            <img src="logo1.png" width="160" height="50" onmouseover="hover(this)" onmouseout="unhover(this)">
        <nav>
            <%if((String)session.getAttribute("loginName")!=null){%>
                <pre>Hola, ${loginName} <a href="${pageContext.request.contextPath}/logout">Logout</a></pre>

            <%}%>
        </nav>
        </header>
        <div class="main">
        <hr>
        <div class="buscar">
            <form action="index.jsp?pag=1">
                <input type="text" id="titulo" name="titulo" placeholder="Titulo" class="form-content">
                <input type="text" id="autor" name="autor" placeholder="Autor"  class="form-content">
                <button type="submit">Search</button>
            </form>
        </div>
        <hr>
        <table>
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
                        <button type="submit" name="boton" id="boton"><img src="descargar.png" height="30" width="30.8"></button>
                    </form></td>
                    <%}%>
                    <td><%=libro.getResumen()%></td>
                    <td><%=libro.getDescargas()%></td>
        </tr>
                    <%}
                    %>

        </table>
        <hr>
        <div class="pag">
            <%if(pag>1){%>
            <button onclick="window.location.href='index.jsp?pag=<%=pag-1%>&titulo=<%=titulo%>&autor=<%=autor%>'">Anterior</button>
            <%}else{%>
            <button disabled="disabled">Anterior</button>
            <%}%>
            <%if(pag*5>=libros.size()){%>
            <button disabled="disabled">Siguiente</button>
            <%}else{%>
            <button onclick="window.location.href='index.jsp?pag=<%=pag+1%>&titulo=<%=titulo%>&autor=<%=autor%>'">Siguiente</button>
            <%}%>
        <%for(int j = 1; j<=controlLibros.busquedaLibrosjson(titulo, autor).size()/5; j++){
            if(pag==j){%>
            <span><%=pag%></span>
            <%}else{%>
            <a href="index.jsp?pag=<%=j%>&titulo=<%=titulo%>&autor=<%=autor%>"><%=j%></a>
        <%}}
        %>
        </div>
            <div style="
    justify-content: center;
    align-items: center;
    text-align: center; padding-top: 5px;">
                <button onclick="window.print()" >Imprimir</button>
            </div>
        </div>
        <footer class="autor">
            <p>David Navarro Jimena (Universidad de Malaga)</p>
            <p>Copyright 2023 Todos los derechos reservados</p>
        </footer>
    </body>
</html>