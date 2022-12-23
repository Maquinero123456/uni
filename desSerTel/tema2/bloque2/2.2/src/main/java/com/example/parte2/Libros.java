package com.example.parte2;

//Clase con toda la informacion de un libro
//Solo contiene getter y setters
public class Libros {
    private String titulo, autor, enlace, resumen;
    private Long descargas;

    public Libros(String titulo, String autor, String enlace, String resumen, Long descargas){
        this.titulo = titulo;
        this.autor = autor;
        this.enlace = enlace;
        this.resumen = resumen;
        this.descargas = descargas;
    }

    public Long getDescargas() {
        return descargas;
    }

    public void setDescargas(Long descargas) {
        this.descargas = descargas;
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

    @Override
    public String toString() {
        return "Libros{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", enlace='" + enlace + '\'' +
                ", resumen='" + resumen + '\'' +
                '}';
    }
}
