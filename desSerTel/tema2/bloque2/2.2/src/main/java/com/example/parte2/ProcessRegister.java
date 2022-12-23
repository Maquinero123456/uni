package com.example.parte2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

//Servlet para el registro
@WebServlet(name="register", value="/register")
public class ProcessRegister extends HttpServlet {
    //Metodo post
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Cargamos los valores del formulario
        String loginName = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        //Comprobamos si estos son lo suficientemente largos
        //En caso contrario, devolvemos error
        if(loginName.length()<3 || password.length()<3){
            request.setAttribute("error", "2");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        //Si añadimos el usuario correctamente, redigirimos a la pagina de inicio
        }else if(FileUsers.addUser(loginName, password)){
            response.sendRedirect(request.getContextPath()+"");
        //Si sucede algun error, devolvemos error
        }else{
            request.setAttribute("error", "1");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

    }

    //Metodo get
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Vemos si hay una sesion activa
        String loginName = LoginManager.getLoginName(request);
        //Si la sesion esta activa, cargamos index
        if(loginName!=null){
            request.setAttribute("loginName", loginName);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        //En caso contrario, cargamos la pagina de registro
        }else{
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
