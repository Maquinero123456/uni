package es.uma.rysd.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

import es.uma.rysd.entities.*;

public class SWClient {
	// TODO: Complete el nombre de la aplicacin
    private final String app_name = "David";
    private final int year = 2023;
    
    private final String url_api = "https://swapi.dev/api/";

    // Mtodos auxiliares facilitados
    
    // Obtiene la URL del recurso id del tipo resource
	public String generateEndpoint(String resource, Integer id){
		return url_api + resource + "/" + id + "/";
	}
	
	// Dada una URL de un recurso obtiene su ID
	public Integer getIDFromURL(String url){
		String[] parts = url.split("/");

		return Integer.parseInt(parts[parts.length-1]);
	}
	
	// Consulta un recurso y devuelve cuntos elementos tiene
	public int getNumberOfResources(String resource){    	
		int count = -1;
		try {
			URL url = new URL(url_api+resource);
			HttpsURLConnection conexion = null;
			conexion = (HttpsURLConnection) url.openConnection();
			conexion.addRequestProperty("Accept", "application/json");
			conexion.setRequestProperty("User-Agent", app_name);
			conexion.setRequestMethod("GET");
			if(conexion.getResponseCode()>=200 && conexion.getResponseCode()<300){
				System.out.println("Codigo correcto "+conexion.getResponseCode());
				Gson parser = new Gson();
				InputStream in=null;
				in = conexion.getInputStream();
				ResourceCountResult c = parser.fromJson(new InputStreamReader(in), ResourceCountResult.class);
				count = c.count;
			}else{
				System.out.println("Codigo incorrecto "+conexion.getResponseCode());
			}
		} catch (IOException e) {
		}
        return count;
	}
	
	public Person getPerson(String urlname) {
    	Person p = null;
    	urlname = urlname.replaceAll("http:", "https:");
		try{   	
			URL url = new URL(urlname);
			HttpsURLConnection conexion = (HttpsURLConnection) url.openConnection();
			conexion.addRequestProperty("Accept", "application/json");
			conexion.setRequestProperty("User-Agent", app_name);
			conexion.setRequestMethod("GET");
			if(conexion.getResponseCode()>=200 && conexion.getResponseCode()<300){
				System.out.println("Codigo correcto "+conexion.getResponseCode());
				Gson parser = new Gson();
				InputStream in=conexion.getInputStream();
				p = parser.fromJson(new InputStreamReader(in), Person.class);
				p.homeplanet = getWorld(p.homeworld);
			}else{
				System.out.println("Codigo incorrecto "+conexion.getResponseCode());
			}
		}catch(IOException e){
		}
		return p;
	}

	public World getWorld(String urlname) {
    	World p = null;
    	urlname = urlname.replaceAll("http:", "https:");
		try{
			URL url = new URL(urlname);
			HttpsURLConnection conexion = (HttpsURLConnection) url.openConnection();
			conexion.addRequestProperty("Accept", "application/json");
			conexion.setRequestProperty("User-Agent", app_name);
			conexion.setRequestMethod("GET");
			if(conexion.getResponseCode()>=200 && conexion.getResponseCode()<300){
				System.out.println("Codigo correcto "+conexion.getResponseCode());
				Gson parser = new Gson();
				InputStream in=conexion.getInputStream();
				World c = parser.fromJson(new InputStreamReader(in), World.class);
				p = c;
			}else{
				System.out.println("Codigo incorrecto "+conexion.getResponseCode());
			}
		}catch(IOException e){
		}
        return p;
	}

	public Person search(String name){
    	Person p = null;
		try{
			URL url = new URL(url_api+"people/?search="+name);
			HttpsURLConnection conexion = (HttpsURLConnection) url.openConnection();
			conexion.addRequestProperty("Accept", "application/json");
			conexion.setRequestProperty("User-Agent", app_name);
			conexion.setRequestMethod("GET");
			if(conexion.getResponseCode()>=200 && conexion.getResponseCode()<300){
				System.out.println("Codigo correcto "+conexion.getResponseCode());
				Gson parser = new Gson();
				InputStream in=conexion.getInputStream();
				QueryResponse query = parser.fromJson(new InputStreamReader(in), QueryResponse.class);
				p = query.results[0];
				p.homeplanet = getWorld(p.homeworld);
			}else{
				System.out.println("Codigo incorrecto "+conexion.getResponseCode());
			}
		}catch(IOException e){
		}catch(ArrayIndexOutOfBoundsException e){
		}
        return p;
    }

}
