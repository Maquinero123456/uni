<%@ page import="com.example.parte2.controlLibros" %>
<%@ page import="com.example.parte2.Libros" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
    </head>
    <body>
        <%if((String)session.getAttribute("loginName")!=null){%>
        <pre>User: ${loginName}        <a href="${pageContext.request.contextPath}/logout">Logout</a></pre>
        <hr>
        <%}%>
        <table>
            <tr>
                <th>Titulo</th>
                <th>Autor</th>
                <th>Enlace</th>
                <th>Resumen</th>
            </tr>
                

                    <%
                        int pag = 1;
                        try{
                            pag = Integer.parseInt(request.getParameter("pag"));
                        }catch(NumberFormatException e){}

                        String autor = request.getParameter("autor");
                        String titulo = request.getParameter("titulo");
                        System.out.println(autor);
                        System.out.println(titulo);
                        ArrayList<Libros> libros = controlLibros.busquedaLibros(titulo, autor);
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
                    <td><%=libro.getEnlace()%></td>
                    <%}%>
                    <td><%=libro.getResumen()%></td>
        </tr>
                    <%}
                    %>

        </table>
        <hr>
        <%if(pag>1){%>
        <button onclick="window.location.href='index.jsp?pag=<%=pag-1%>&titulo=<%=titulo%>&autor=<%=autor%>'">Anterior</button>
        <%}else{%>
        <button disabled="disabled">Anterior</button>
        <%}%>
        <%if(pag*5>libros.size()){%>
        <button disabled="disabled">Siguiente</button>
        <%}else{%>
        <button onclick="window.location.href='index.jsp?pag=<%=pag+1%>&titulo=<%=titulo%>&autor=<%=autor%>'">Siguiente</button>
        <%}%>
        <hr>
        <form action="index.jsp?pag=1">
            <label for="titulo">Titulo: </label>
            <input type="text" id="titulo" name="titulo">
            <label for="autor">Autor: </label>
            <input type="text" id="autor" name="autor">
            <input type="submit" value="Buscar">
        </form>
        <hr>
    </body>
</html>