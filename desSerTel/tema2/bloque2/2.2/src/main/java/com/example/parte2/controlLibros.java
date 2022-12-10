package com.example.parte2;

import java.util.ArrayList;
import java.io.*;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.*;

public class controlLibros {
    private static String path = "/home/maqui/uni/desSerTel/tema2/bloque2/2.2/src/main/webapp/libros.json";
    //private static String path = "C:\\Users\\david\\Desktop\\uni\\desSerTel\\tema2\\bloque2\\2.2\\src\\main\\webapp\\documentos.txt";
    /*public static ArrayList<Libros> listaLibros() throws IOException{
        File archivo = new File(path);
        BufferedReader b = new BufferedReader(new FileReader(archivo));
        String line = "";
        String titulo = "", autor="", enlace="", resumen="";
        ArrayList<Libros> libros = new ArrayList<>();
        int contador = 0;
        while((line = b.readLine())!=null || contador!=0){


            if(line==null || line.equals("")){
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

    public static ArrayList<Libros> listaLibrosAutor(String autorBusqueda) throws IOException{
        File archivo = new File(path);
        BufferedReader b = new BufferedReader(new FileReader(archivo));
        String line = "";
        String titulo = "", autor="", enlace="", resumen="";
        ArrayList<Libros> libros = new ArrayList<>();
        int contador = 0;
        while((line = b.readLine())!=null || contador!=0){


            if(line==null || line.equals("")){
                if(autor.contains(autorBusqueda)){
                    libros.add(new Libros(titulo, autor, enlace, resumen));
                }
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

    public static ArrayList<Libros> listaLibrosTitulo(String tituloBusqueda) throws IOException{
        File archivo = new File(path);
        BufferedReader b = new BufferedReader(new FileReader(archivo));
        String line = "";
        String titulo = "", autor="", enlace="", resumen="";
        ArrayList<Libros> libros = new ArrayList<>();
        int contador = 0;
        while((line = b.readLine())!=null || contador!=0){


            if(line==null || line.equals("")){
                if(titulo.contains(tituloBusqueda)){
                    libros.add(new Libros(titulo, autor, enlace, resumen));
                }
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

    public static ArrayList<Libros> listaLibrosAutorTitulo(String autorBusqueda, String tituloBusqueda) throws IOException{
        File archivo = new File(path);
        BufferedReader b = new BufferedReader(new FileReader(archivo));
        String line = "";
        String titulo = "", autor="", enlace="", resumen="";
        ArrayList<Libros> libros = new ArrayList<>();
        int contador = 0;
        while((line = b.readLine())!=null || contador!=0){
            if(line==null || line.equals("")){
                if(titulo.contains(tituloBusqueda)&&autor.contains(autorBusqueda)){
                    libros.add(new Libros(titulo, autor, enlace, resumen));
                }
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

    public static ArrayList<Libros> busquedaLibros(String titulo, String autor) throws IOException {
        if(titulo==null&&autor==null){
            return listaLibros();
        }else if(titulo==null){
            return listaLibrosAutor(autor);
        }else if(autor==null){
            return listaLibrosTitulo(titulo);
        }
        return listaLibrosAutorTitulo(autor, titulo);
    }*/

    public static ArrayList<Libros> listaLibrosjson() throws IOException, ParseException {
        ArrayList<Libros> libros = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader(path);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        JSONArray array = (JSONArray) jsonObject.get("libros");
        JSONObject aux = (JSONObject) array.get(0) ;
        int a = 1;
        JSONObject aux2 = null;
        while((aux2=(JSONObject) aux.get("libro"+a))!=null){
            libros.add(new Libros((String) aux2.get("titulo"), (String) aux2.get("autor"), (String) aux2.get("enlace"), (String) aux2.get("resumen"), (Long) aux2.get("nDescargas")));
            a++;
        }
        return libros;
    }

    public static ArrayList<Libros> listaLibrosAutorjson(String autor) throws IOException, ParseException {
        ArrayList<Libros> libros = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader(path);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        JSONArray array = (JSONArray) jsonObject.get("libros");
        JSONObject aux = (JSONObject) array.get(0) ;
        int a = 1;
        JSONObject aux2 = null;
        while((aux2=(JSONObject) aux.get("libro"+a))!=null){
            if(((String)aux2.get("autor")).contains(autor)){
                libros.add(new Libros((String) aux2.get("titulo"), (String) aux2.get("autor"), (String) aux2.get("enlace"), (String) aux2.get("resumen"), (Long) aux2.get("nDescargas")));
            }
            a++;
        }
        return libros;
    }
    public static ArrayList<Libros> listaLibrosTitulojson(String titulo) throws IOException, ParseException {
        ArrayList<Libros> libros = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader(path);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        JSONArray array = (JSONArray) jsonObject.get("libros");
        JSONObject aux = (JSONObject) array.get(0) ;
        int a = 1;
        JSONObject aux2 = null;
        while((aux2=(JSONObject) aux.get("libro"+a))!=null){
            if(((String)aux2.get("titulo")).contains(titulo)){
                libros.add(new Libros((String) aux2.get("titulo"), (String) aux2.get("autor"), (String) aux2.get("enlace"), (String) aux2.get("resumen"), (Long) aux2.get("nDescargas")));
            }
            a++;
        }
        return libros;
    }
    public static ArrayList<Libros> listaLibrosAutorTitulojson(String autor, String titulo) throws IOException, ParseException {
        ArrayList<Libros> libros = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader(path);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        JSONArray array = (JSONArray) jsonObject.get("libros");
        JSONObject aux = (JSONObject) array.get(0) ;
        int a = 1;
        JSONObject aux2 = null;
        while((aux2=(JSONObject) aux.get("libro"+a))!=null){
            if(((String)aux2.get("autor")).contains(autor) && ((String)aux2.get("titulo")).contains(titulo)){
                libros.add(new Libros((String) aux2.get("titulo"), (String) aux2.get("autor"), (String) aux2.get("enlace"), (String) aux2.get("resumen"), (Long) aux2.get("nDescargas")));
            }
            a++;
        }
        return libros;
    }
    public static ArrayList<Libros> busquedaLibrosjson(String titulo, String autor) throws IOException, ParseException {
        if(titulo==""&&autor==""){
            return listaLibrosjson();
        }else if(titulo==""){
            return listaLibrosAutorjson(autor);
        }else if(autor==""){
            return listaLibrosTitulojson(titulo);
        }
        return listaLibrosAutorTitulojson(autor, titulo);
    }

    public static void updateDescargas(String titulo, String autor) throws IOException, ParseException {
        System.out.println(autor);
        System.out.println(titulo);
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader(path);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        JSONArray array = (JSONArray) jsonObject.get("libros");
        JSONObject aux = (JSONObject) array.get(0) ;
        int a = 1;
        JSONObject aux2 = null;
        String salida = "";
        while((aux2=(JSONObject) aux.get("libro"+a))!=null){
            if(((String)aux2.get("autor")).contains(autor) && ((String)aux2.get("titulo")).contains(titulo)){

                System.out.println(aux2.get("autor"));
                System.out.println(aux2.get("titulo"));
                aux2.put("nDescargas", ((Long) aux2.get("nDescargas"))+1);
            }
            salida += "\"libro"+a+"\":"+aux2+",";
            a++;
        }

        salida = salida.substring(0, salida.length()-1);
        salida = "{\"libros\":[{"+salida+"}]}";
        org.json.JSONObject json = new org.json.JSONObject(salida);

        FileWriter file = new FileWriter(path);
        file.write(json.toString());
        file.flush();
        file.close();
    }
}
