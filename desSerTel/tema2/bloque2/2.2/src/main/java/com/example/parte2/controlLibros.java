package com.example.parte2;

import java.util.ArrayList;
import java.io.*;

public class controlLibros {
    private static String path = "/home/maqui/uni/desSerTel/tema2/bloque2/2.2/src/main/webapp/documentos.txt";
    public static ArrayList<Libros> listaLibros() throws IOException{
        File archivo = new File(path);
        BufferedReader b = new BufferedReader(new FileReader(archivo));
        String line = "";
        String titulo = "", autor="", enlace="", resumen="";
        ArrayList<Libros> libros = new ArrayList<>();
        int contador = 0;
        while((line = b.readLine())!=null || contador!=0){


            if(line==null || line.equals("")){
                System.out.println(resumen);
                System.out.println(new Libros(titulo, autor, enlace, resumen));
                libros.add(new Libros(titulo, autor, enlace, resumen));
                contador=0;
            }else{
                if(contador==0){
                    System.out.println("Titulo: ");
                    System.out.println(line);
                    titulo = line;
                }else if(contador==1){
                    System.out.println("Autor: ");
                    System.out.println(line);
                    autor = line;
                }else if(contador==2){
                    System.out.println("Enlace: ");
                    System.out.println(line);
                    enlace = line;
                }else if(contador==3){
                    resumen = line;
                    System.out.println("Resumen: ");
                    System.out.println(resumen);
                }
                contador++;
            }
        }
        b.close();
        return libros;
    }
}
