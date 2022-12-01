package com.example.parte2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "logout", value = "/logout")
public class ProcessLogoutServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        LoginManager.logout(request);
        response.sendRedirect(request.getContextPath()+"/MainPage");
    }
}
