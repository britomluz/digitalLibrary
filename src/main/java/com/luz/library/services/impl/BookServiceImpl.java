package com.luz.library.services.impl;

import com.luz.library.dtos.BookCreateDTO;
import com.luz.library.dtos.BookDTO;
import com.luz.library.models.Book;
import com.luz.library.repositories.BookRepository;
import com.luz.library.services.BookService;
import com.luz.library.services.specifications.BookSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public Optional<Book> getById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book getByTitulo(String titulo) {
        return bookRepository.findByTitulo(titulo);
    }

    @Override
    public List<Book> getAllByBestSellers(Boolean bestSeller) {
        return bookRepository.findAllByBestSeller(bestSeller);
    }

    @Override
    public Page<Book> getBookByFilter(BookDTO filter, Pageable pageable) {
        return bookRepository.findAll(BookSpecifications.bookFilter(filter), pageable);
    }

    @Override
    public void saveBook(BookCreateDTO bookCreateDTO) {
        bookRepository.save(Book.builder()
                        .titulo(bookCreateDTO.getTitulo())
                        .autor(bookCreateDTO.getAutor())
                        .categoria(bookCreateDTO.getCategoria())
                        .editorial(bookCreateDTO.getEditorial())
                        .precio(Double.parseDouble(bookCreateDTO.getPrecio()))
                        .fechaLanzamiento(LocalDate.parse(bookCreateDTO.getFechaLanzamiento()))
                        .portada(bookCreateDTO.getPortada())
                        .resenia(bookCreateDTO.getResenia())
                        .bestSeller(Boolean.FALSE)
                        .build());
    }
}
