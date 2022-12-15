package com.example.parte2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name="login", value = "/login")
public class ProcessLogin extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String loginName = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        if(loginName.length()<3 || password.length()<3){
            request.setAttribute("error", "2");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }else if(FileUsers.login(loginName, password)){
            LoginManager.login(request, loginName.trim(), password);
            response.sendRedirect(request.getContextPath()+"");
        }else{
            request.setAttribute("error", "1");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }


    }
}
