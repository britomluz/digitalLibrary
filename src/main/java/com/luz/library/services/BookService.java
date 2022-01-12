package com.luz.library.services;

import com.luz.library.dtos.BookCreateDTO;
import com.luz.library.dtos.BookDTO;
import com.luz.library.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public Book save(Book book);
    public List<Book> getAll();
    public void delete(Book book);
    public Optional<Book> getById(Long id);
    public Book getByTitulo(String titulo);
    public List<Book> getAllByBestSellers(Boolean bestSeller);
    public Page<Book> getBookByFilter(BookDTO filter, Pageable pageable);
    public void saveBook(BookCreateDTO bookCreateDTO);
}
