package com.example.parte2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="recuperarPassword", value="/recuperarPassword")
public class recuperarPassword extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String loginName = request.getParameter("username").trim();
        if(FileUsers.searchUser(loginName)){
            request.setAttribute("password", FileUsers.getPassword(loginName));
            request.getRequestDispatcher("recuperarPassword.jsp").forward(request, response);
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
