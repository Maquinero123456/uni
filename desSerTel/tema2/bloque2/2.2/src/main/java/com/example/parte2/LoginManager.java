package com.example.parte2;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

//Clase que se encarga de lo relacionado con el login
public final class LoginManager{
    private final static String LOGIN_NAME_ATTRIBUTE = "loginName";

    private LoginManager(){}

    //Metodo para crear sesion
    public final static void login(HttpServletRequest request, String loginName, String password){
        HttpSession session = request.getSession(true);
        session.setAttribute(LOGIN_NAME_ATTRIBUTE, loginName);
    }

    //Metodo para eliminar la sesion
    public final static void logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
    }

    //Metodo para devolver el nombre de usuario en la sesion
    public final static String getLoginName(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session==null){
            return null;
        }else{
            return (String) session.getAttribute(LOGIN_NAME_ATTRIBUTE);
        }
    }
}