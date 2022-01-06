package com.luz.library.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String titulo;
    private String autor;
    private String editorial;
    private String categoria;
    private Double precio;
    private LocalDate fechaLanzamiento;
    private String portada;
    private String resenia;
    private BookType type;
    private BookBestSeller bestSeller;


    public Book() { }

    public Book(String titulo, String autor, String editorial, String categoria, Double precio, LocalDate fechaLanzamiento, String portada, String resenia, BookType type, BookBestSeller bestSeller) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.categoria = categoria;
        this.precio = precio;
        this.fechaLanzamiento = fechaLanzamiento;
        this.portada = portada;
        this.resenia = resenia;
        this.type = type;
        this.bestSeller = bestSeller;

    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo.toUpperCase();
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
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

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BookBestSeller getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(BookBestSeller bestSeller) {
        this.bestSeller = bestSeller;
    }
}
