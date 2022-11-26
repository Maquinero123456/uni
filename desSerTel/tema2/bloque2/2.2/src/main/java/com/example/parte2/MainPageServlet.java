package com.example.parte2;

import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet(name = "MainPage", value = "/*")
public class MainPageServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String loginName = LoginManager.getLoginName(request);
        if(loginName!=null){
            request.setAttribute("loginName", loginName);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
