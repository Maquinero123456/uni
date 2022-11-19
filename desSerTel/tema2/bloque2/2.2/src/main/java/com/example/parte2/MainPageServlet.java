package com.example.parte2;

import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet(name = "MainPage", value = "/MainPage")
public class MainPageServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String loginName = LoginManager.getLoginName(request);
        loginName= "Juan";
        if(loginName!=null){
            request.setAttribute("loginName", loginName);
        }else{
            System.out.println("Hola");
        }
    }
}
