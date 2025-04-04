package com.example.parte2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

@WebServlet(name="register", value="/register")
public class ProcessRegister extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String loginName = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        if(loginName.length()<3 || password.length()<3){
            request.setAttribute("error", "2");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }else if(FileUsers.addUser(loginName, password)){
            response.sendRedirect(request.getContextPath()+"");
        }else{
            request.setAttribute("error", "1");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String loginName = LoginManager.getLoginName(request);
        if(loginName!=null){
            request.setAttribute("loginName", loginName);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
