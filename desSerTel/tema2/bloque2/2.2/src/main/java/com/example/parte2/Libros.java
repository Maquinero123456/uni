package com.example.parte2;

public class Libros {
    private String titulo, autor, enlace, resumen;

    public Libros(String titulo, String autor, String enlace, String resumen){
        this.titulo = titulo;
        this.autor = autor;
        this.enlace = enlace;
        this.resumen = enlace;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEnlace() {
        return this.enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getResumen() {
        return this.resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

}
