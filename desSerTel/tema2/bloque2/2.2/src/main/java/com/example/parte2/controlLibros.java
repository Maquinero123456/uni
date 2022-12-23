package com.example.parte2;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.io.*;
import java.util.Iterator;
import java.util.Set;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import org.json.simple.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.*;

public class controlLibros {
    //private static String path = "/home/maqui/uni/desSerTel/tema2/bloque2/2.2/src/main/webapp/libros.json";
    private static String path = "C:\\Users\\david\\Desktop\\uni\\desSerTel\\tema2\\bloque2\\2.2\\src\\main\\webapp\\libros.json";

    //Devuelve la lista completa de libros
    public static ArrayList<Libros> listaLibrosjson() throws IOException, ParseException {
        ArrayList<Libros> libros = new ArrayList<>();
        //Cargamos el pdf y nos quedamos con los libros
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader(path);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        JSONArray array = (JSONArray) jsonObject.get("libros");
        JSONObject aux = (JSONObject) array.get(0) ;
        //Creamos un numero para iterar y un JSONObject para guardar los valores
        int a = 1;
        JSONObject aux2 = null;
        //Iteramos sobre el json y vamos añadiendo los libros a la lista
        while((aux2=(JSONObject) aux.get("libro"+a))!=null){
            libros.add(new Libros((String) aux2.get("titulo"),
                                  (String) aux2.get("autor"),
                                  (String) aux2.get("enlace"),
                                  (String) aux2.get("resumen"),
                                  (Long) aux2.get("nDescargas")));
            a++;
        }
        return libros;
    }

    //Devolvemos la lista de libros con el autor que queremos
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
            //Si el autor del libro actual es el que queremos lo devolvemos
            if(((String)aux2.get("autor")).contains(autor)){
                libros.add(new Libros((String) aux2.get("titulo"), (String) aux2.get("autor"), (String) aux2.get("enlace"), (String) aux2.get("resumen"), (Long) aux2.get("nDescargas")));
            }
            a++;
        }
        return libros;
    }

    //Devolvemos la lista de libros con el titulo que queremos
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
            //Si el titulo del libro actual es el que queremos lo devolvemos
            if(((String)aux2.get("titulo")).contains(titulo)){
                libros.add(new Libros((String) aux2.get("titulo"), (String) aux2.get("autor"), (String) aux2.get("enlace"), (String) aux2.get("resumen"), (Long) aux2.get("nDescargas")));
            }
            a++;
        }
        return libros;
    }

    //Devolvemos los libros con el autor y titulo que queremos
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
            //Si el titulo y autor son los que queremos, lo guardamos
            if(((String)aux2.get("autor")).contains(autor) && ((String)aux2.get("titulo")).contains(titulo)){
                libros.add(new Libros((String) aux2.get("titulo"), (String) aux2.get("autor"), (String) aux2.get("enlace"), (String) aux2.get("resumen"), (Long) aux2.get("nDescargas")));
            }
            a++;
        }
        return libros;
    }

    //Dependiendo de la entrada llamamos a un metodo u otro
    public static ArrayList<Libros> busquedaLibrosjson(String titulo, String autor) throws IOException, ParseException {
        //Si ambos son vacios devolvemos todos
        if(titulo==""&&autor==""){
            return listaLibrosjson();
        //Si el titulo esta vacio, buscamos por el autor
        }else if(titulo==""){
            return listaLibrosAutorjson(autor);
        //Si el autor esta vacio, buscamos por el titulo
        }else if(autor==""){
            return listaLibrosTitulojson(titulo);
        }
        //Buscamos por autor y titulo
        return listaLibrosAutorTitulojson(autor, titulo);
    }

    //Actualizamos las descargas de un libro
    public static void updateDescargas(String titulo, String autor) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader(path);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        JSONArray array = (JSONArray) jsonObject.get("libros");
        JSONObject aux = (JSONObject) array.get(0) ;
        int a = 1;
        JSONObject aux2 = null;
        //Creamos un string para la salida
        String salida = "";
        while((aux2=(JSONObject) aux.get("libro"+a))!=null){
            //Si el titulo y autor son los que buscamos, actualizamos el numero de descargas
            if(((String)aux2.get("autor")).contains(autor) && ((String)aux2.get("titulo")).contains(titulo)){
                aux2.put("nDescargas", ((Long) aux2.get("nDescargas"))+1);
            }
            //Tras esto guardamos en el string de salida el libro actual
            salida += "\"libro"+a+"\":"+aux2+",";
            a++;
        }
        //Creamos un json con el string de salida
        salida = salida.substring(0, salida.length()-1);
        salida = "{\"libros\":[{"+salida+"}]}";
        org.json.JSONObject json = new org.json.JSONObject(salida);

        //Escribimos el json en el archivo
        FileWriter file = new FileWriter(path);
        file.write(json.toString());
        file.flush();
        file.close();
    }
}
