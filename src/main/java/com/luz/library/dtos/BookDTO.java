package com.luz.library.dtos;

import com.luz.library.models.Book;
import com.luz.library.models.BookBestSeller;
import com.luz.library.models.BookType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookDTO {
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

    public BookDTO(Book book) {
        this.id = book.getId();
        this.titulo = book.getTitulo();
        this.autor = book.getAutor();
        this.editorial = book.getEditorial();
        this.categoria = book.getCategoria();
        this.precio = book.getPrecio();
        this.fechaLanzamiento = book.getFechaLanzamiento();
        this.portada = book.getPortada();
        this.resenia = book.getResenia();
        this.type = book.getType();
        this.bestSeller = book.getBestSeller();
    }


    public Long getId() {
        return id;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getFechaLanzamiento() {
        DateTimeFormatter dateFormatt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateFromString = fechaLanzamiento.format(dateFormatt);
        return dateFromString;
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
