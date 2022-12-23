package com.example.parte2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//Servlet para recuperar contraseña
@WebServlet(name="recuperarPassword", value="/recuperarPassword")
public class recuperarPassword extends HttpServlet {

    //Metodo post al que llamamos con un formulario
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Cargamos el usuario
        String loginName = request.getParameter("username").trim();
        //Si existe el usuario, devolvemos la contraseña
        if(FileUsers.searchUser(loginName)){
            request.setAttribute("password", FileUsers.getPassword(loginName));
            request.getRequestDispatcher("recuperarPassword.jsp").forward(request, response);
        //En caso contrario devolvemos error
        }else{
            request.setAttribute("error", 1);
            request.getRequestDispatcher("recuperarPassword.jsp").forward(request, response);
        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("recuperarPassword.jsp").forward(request, response);
    }

}
