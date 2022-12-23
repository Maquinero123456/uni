package com.example.parte2;

import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

//Servlet inicial
@WebServlet(name = "MainPage", value = "")
public class MainPageServlet extends HttpServlet{

    public void init() {

    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        //Cargamos la sesion
        String loginName = LoginManager.getLoginName(request);
        //Si hay sesion iniciada, vamos a index
        if(loginName!=null){
            request.setAttribute("loginName", loginName);
            request.getRequestDispatcher("index.jsp?pag=1&titulo=&autor=").forward(request, response);
        //Si no hay sesion iniciada, vamos a login
        }else{
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    public void destroy() {
    }
}
