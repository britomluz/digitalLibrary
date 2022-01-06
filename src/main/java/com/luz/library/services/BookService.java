package com.luz.library.services;

import com.luz.library.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public Book save(Book book);
    public List<Book> getAll();
    public void delete(Book book);
    public Optional<Book> getById(Long id);
    public Book findByTitulo(String titulo);
}
