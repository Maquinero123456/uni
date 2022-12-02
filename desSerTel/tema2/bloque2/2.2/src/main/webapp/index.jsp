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
        <p>User: ${loginName}</p><a href="${pageContext.request.contextPath}/logout">Logout</a><br>
        <table>
            <tr>
                <th>Titulo</th>
                <th>Autor</th>
                <th>Enlace</th>
                <th>Resumen</th>
            </tr>
                

                    <%
                        ArrayList<Libros> libros = controlLibros.listaLibros();
                        int pag = 1;
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
                    <td><%=libro.getEnlace()%></td>
                    <td><%=libro.getResumen()%></td>
        </tr>
                    <%}
                    %>

        </table>
        <button>Anterior</button><button>Siguiente</button>
    </body>
</html>