package com.luz.library.dtos;

import com.luz.library.models.BookType;

import java.time.LocalDate;

public class BookCreateDTO {
    private String titulo;
    private String autor;
    private String editorial;
    private String categoria;
    private String precio;
    private String fechaLanzamiento;
    private String portada;
    private String resenia;



    public BookCreateDTO(String titulo, String autor, String editorial, String categoria, String precio, String fechaLanzamiento, String portada, String resenia) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.categoria = categoria;
        this.precio = precio;
        this.fechaLanzamiento = fechaLanzamiento;
        this.portada = portada;
        this.resenia = resenia;

    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getResenia() {
        return resenia;
    }

    public void setResenia(String resenia) {
        this.resenia = resenia;
    }

}
