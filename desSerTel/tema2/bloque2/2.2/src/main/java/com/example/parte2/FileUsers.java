package com.example.parte2;

import java.io.*;

public class FileUsers {
    private static final String path = "C:\\Users\\david\\Desktop\\uni\\desSerTel\\tema2\\bloque2\\2.2\\src\\main\\webapp\\users.txt";
    //private static final String path = "/home/maqui/uni/desSerTel/tema2/bloque2/2.2/src/main/webapp/users.txt";


    public static boolean searchUser(String username){
        try {
            File f = new File(path);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String line = "";

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

    public static boolean addUser(String username, String password){
        if(!searchUser(username)){
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
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

    public static boolean login(String username, String password){
        if(searchUser(username)){
            try {
                File f = new File(path);
                BufferedReader b = new BufferedReader(new FileReader(f));
                String line = "";

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

    public static boolean printUsers(){
        try {
            File f = new File(path);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String line = "";
            b.close();
            return false;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String getPassword(String username){
        try {
            File f = new File(path);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String line = "";

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
