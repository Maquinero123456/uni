package com.example.parte2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//Servlet para logout
@WebServlet(name = "logout", value = "/logout")
public class ProcessLogoutServlet extends HttpServlet {

    //Metodo get
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        //Cerramos sesion y volvemos a inicio
        LoginManager.logout(request);
        response.sendRedirect(request.getContextPath()+"");
    }
}
