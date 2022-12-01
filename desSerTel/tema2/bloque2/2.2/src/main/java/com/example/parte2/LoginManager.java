package com.example.parte2;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public final class LoginManager{
    private final static String LOGIN_NAME_ATTRIBUTE = "loginName";

    private LoginManager(){}

    public final static void login(HttpServletRequest request, String loginName, String password){
        if(FileUsers.login(loginName, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute(LOGIN_NAME_ATTRIBUTE, loginName);
        }
    }

    public final static void logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
    }

    public final static String getLoginName(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session==null){
            return null;
        }else{
            return (String) session.getAttribute(LOGIN_NAME_ATTRIBUTE);
        }
    }
}