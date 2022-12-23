package com.example.parte2;

import java.io.*;

public class FileUsers {
    private static final String path = "C:\\Users\\david\\Desktop\\uni\\desSerTel\\tema2\\bloque2\\2.2\\src\\main\\webapp\\users.txt";
    //private static final String path = "/home/maqui/uni/desSerTel/tema2/bloque2/2.2/src/main/webapp/users.txt";

    //Metodo para ver si un usuario ya existe
    public static boolean searchUser(String username){
        try {
            //Cargamos el archivo
            File f = new File(path);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String line = "";
            //Leemos cada linea y si el usuario es igual al que buscamos, devolvemos True
            while((line = b.readLine())!=null){
                String[] partes = line.split(" ");
                if(partes[0].equals(username)){
                    b.close();
                    return true;
                }
            }
            b.close();
            return false;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //Añadimos un usuario al archivo
    public static boolean addUser(String username, String password){
        //Primero comprobamos si el usuario ya existe
        if(!searchUser(username)){
            //Cargamos el archivo para escritura
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
                //Añadimos un usuario al final del fichero
                bw.newLine();
                bw.write(username+" "+password);
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    //Buscamos si un usuario y contraseña coinciden con la de un usuario
    public static boolean login(String username, String password){
        //Primero vemos si el usuario existe
        if(searchUser(username)){
            try {
                //Cargamos el archivo
                File f = new File(path);
                BufferedReader b = new BufferedReader(new FileReader(f));
                String line = "";
                //Leemos el archivo hasta que veamos si existe algun usuario y contraseña que coincidan
                while((line = b.readLine())!=null){
                    String[] partes = line.split(" ");
                    if(partes[0].equals(username) && partes[1].equals(password)){
                        b.close();
                        return true;
                    }
                }
                b.close();
                return false;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    //Metodo para buscar contraseña
    public static String getPassword(String username){
        try {
            //Cargamos fichero
            File f = new File(path);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String line = "";
            //Leemos cada linea, si encontramos el usuario, devolvemos la contraseña
            while((line = b.readLine())!=null){
                String[] partes = line.split(" ");
                if(partes[0].equals(username)){
                    b.close();
                    return partes[1];
                }
            }
            b.close();
            return null;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
