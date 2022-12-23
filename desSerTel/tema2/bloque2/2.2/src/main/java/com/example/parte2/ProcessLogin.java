package com.example.parte2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
//Servlet para login
@WebServlet(name="login", value = "/login")
public class ProcessLogin extends HttpServlet {
    //Metoto post
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        //Cargamos los valores del form
        String loginName = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        //Comprobamos si estos son lo suficientemente largos
        //En caso contrrario devolvemos error
        if(loginName.length()<3 || password.length()<3){
            request.setAttribute("error", "2");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        //Iniciamos sesion
        }else if(FileUsers.login(loginName, password)){
            LoginManager.login(request, loginName.trim(), password);
            response.sendRedirect(request.getContextPath()+"");
        //Si hay algun error al iniciar sesion, devolvemos error
        }else{
            request.setAttribute("error", "1");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }


    }
}
