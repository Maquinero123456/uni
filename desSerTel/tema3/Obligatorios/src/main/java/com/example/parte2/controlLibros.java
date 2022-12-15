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
