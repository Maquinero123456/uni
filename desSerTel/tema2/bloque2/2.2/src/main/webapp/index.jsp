<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
    </head>
    <body>
        <p>User: ${loginName}</p><a href="${pageContext.request.contextPath}/logout">Logout</a><br>
        <% private ArrayList<Libros> libros = controlLibros.listaLibros;
           private int pag = 1;
            %>
        <table>
            <tr>
                <th>Titulo</th>
                <th>Autor</th>
                <th>Enlace</th>
                <th>Resumen</th>
            </tr>
                
                <tr>
                    <%
                    for(int i = pag*5-5; i<pag*5; i++){
                        Libro libro = libros.get(i);
                %>
                    <td><%=libto.titulo%></td>
                    <td><%=libto.autor%></td>
                    <td><%=libto.enlace%></td>
                    <td><%=libto.resumen%></td>
                    <%}
                    %>
                </tr>
        </table>
        <button>Anterior</button><button>Siguiente</button>
    </body>
</html>