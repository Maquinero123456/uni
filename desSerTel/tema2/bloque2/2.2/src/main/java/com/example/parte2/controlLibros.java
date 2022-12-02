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
        while((line = b.readLine())!=null){
            if(line.equals("\n")){
                libros.add(new Libros(titulo, autor, enlace, resumen));
                contador=0;
            }else{
                if(contador==0){
                    titulo = line;
                }else if(contador==1){
                    autor = line;
                }else if(contador==2){
                    enlace = line;
                }else if(contador==3){
                    resumen = line;
                }
                contador++;
            }
        }
        b.close();
        return libros;
    }
}
