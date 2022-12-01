package com.example.parte2;

import jakarta.servlet.http.HttpServletRequest;

public class RegisterManager {

    private RegisterManager(){}

    public static void register(HttpServletRequest request, String username, String password){
        if(FileUsers.addUser(username, password)){

        }
    }
}
