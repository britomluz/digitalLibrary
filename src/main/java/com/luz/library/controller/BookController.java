package com.luz.library.controller;


import com.luz.library.dtos.BookCreateDTO;
import com.luz.library.dtos.BookDTO;
import com.luz.library.models.*;
import com.luz.library.services.impl.BookServiceImpl;
import com.luz.library.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    UserServiceImpl userService;

    //get bookDTO list
    @GetMapping("/api/books")
    public List<BookDTO> getBook() {
        return bookService.getAll().stream().map(BookDTO::new).collect(Collectors.toList());
    }

    //admin view book details
    @GetMapping("/api/book/{id}")
    public ResponseEntity<Object> getOrders(Authentication authentication, @PathVariable Long id){

        User user = userService.getByEmail(authentication.getName());
        Book book = bookService.getById(id).orElse(null);

        if(!user.getRole().equals(UserRole.ADMIN)){
            return new ResponseEntity<>("Permisos insuficientes", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(bookService.getById(id).map(BookDTO::new).orElse(null), HttpStatus.CREATED);
    }

    //admin add book
    @PostMapping("/api/books")
    public ResponseEntity<?> createBook(Authentication authentication, @RequestBody BookCreateDTO bookCreateDTO){
        User user = userService.getByEmail(authentication.getName());

        Double price = Double.parseDouble(bookCreateDTO.getPrecio());
        LocalDate fechaLanzamiento = LocalDate.parse(bookCreateDTO.getFechaLanzamiento());


        if (!user.getRole().equals(UserRole.ADMIN)){
            return new ResponseEntity<>("Permisos insuficientes",HttpStatus.FORBIDDEN);
        }

        if (bookCreateDTO.getTitulo().isEmpty() || bookCreateDTO.getAutor().isEmpty()
            || bookCreateDTO.getEditorial().isEmpty() || bookCreateDTO.getCategoria().isEmpty()
            || bookCreateDTO.getFechaLanzamiento().isEmpty() || bookCreateDTO.getPrecio().isEmpty()
            || bookCreateDTO.getPortada().isEmpty() || bookCreateDTO.getResenia().isEmpty()){
            return new ResponseEntity<>("Campos sin rellenar",HttpStatus.FORBIDDEN);
        }

        if (bookService.getAll().stream().map(book1 -> book1.getTitulo()).collect(Collectors.toList()).contains(bookCreateDTO.getTitulo().toUpperCase())){
            return new ResponseEntity<>("El libro ya existe",HttpStatus.FORBIDDEN);
        }

        if(fechaLanzamiento.isAfter(LocalDate.now())){
            return new ResponseEntity<>("Fecha de Lanzamiento inválida", HttpStatus.FORBIDDEN);
        }

        Book bookNew = new Book(bookCreateDTO.getTitulo().toUpperCase(), bookCreateDTO.getAutor(), bookCreateDTO.getEditorial(), bookCreateDTO.getCategoria(),
                price, fechaLanzamiento, bookCreateDTO.getPortada(), bookCreateDTO.getResenia(), BookType.NOFAV, BookBestSeller.NO);
        bookService.save(bookNew);
        return new ResponseEntity<>("Libro agregado",HttpStatus.CREATED);
    }

    //admin add book to bestSellers
    @PatchMapping("/api/books/bestsellers/edit")
    public ResponseEntity<Object> addBestSeller(Authentication authentication, @RequestParam String bookId) {

        User user = userService.getByEmail(authentication.getName());
        Long id = Long.parseLong(bookId);
        Book book = bookService.getById(id).orElse(null);

        if (!user.getRole().equals(UserRole.ADMIN)){
            return new ResponseEntity<>("Permisos insuficientes",HttpStatus.FORBIDDEN);
        }

        if (bookId.isEmpty()) {
            return new ResponseEntity<>("El campo no puede estar vacío", HttpStatus.BAD_REQUEST);
        }

        if(book.getBestSeller() == BookBestSeller.NO){

            if (bookService.getAll().stream().filter(book1 -> book1.getBestSeller().equals(BookBestSeller.YES)).count() > 9 ) {
                return new ResponseEntity<>("No se puede agregar más de 10 libros a Best Sellers", HttpStatus.FORBIDDEN);
            }
            book.setBestSeller(BookBestSeller.YES);
            bookService.save(book);
            return new ResponseEntity<>("Movido a 'Best Sellers'", HttpStatus.OK);
        }

        if(book.getBestSeller() == BookBestSeller.YES){
            book.setBestSeller(BookBestSeller.NO);
            bookService.save(book);
            return new ResponseEntity<>("Movido a 'General'", HttpStatus.OK);
        }
        return new ResponseEntity<>("Libro editado exitosamente",HttpStatus.OK);
    };

    //admin delete books
    @DeleteMapping("/api/books/delete/{id}")
    public ResponseEntity<?> deleteBook(Authentication authentication, @PathVariable("id") Long id){
        User user = userService.getByEmail(authentication.getName());
        Book book = bookService.getById(id).orElse(null);

        if (!user.getRole().equals(UserRole.ADMIN)){
            return new ResponseEntity<>("Permisos insuficientes",HttpStatus.UNAUTHORIZED);
        }
        if (book== null){
            return new ResponseEntity<>("El libro no existe",HttpStatus.FORBIDDEN);
        }

        bookService.delete(book);
        return new ResponseEntity<>("Libro borrado con éxito",HttpStatus.OK);
    }

    //admin edit book
    @PatchMapping("/api/book/edit/{id}")
    public ResponseEntity<?> editProduct(Authentication authentication, @PathVariable("id") Long id,
                                         @RequestBody BookCreateDTO bookCreateDTO){
        User user = userService.getByEmail(authentication.getName());
        Book book = bookService.getById(id).orElse(null);

        Double price = Double.parseDouble(bookCreateDTO.getPrecio());
        LocalDate fechaLanzamiento = LocalDate.parse(bookCreateDTO.getFechaLanzamiento());


        if (!user.getRole().equals(UserRole.ADMIN)){
            return new ResponseEntity<>("Permisos insuficientes",HttpStatus.FORBIDDEN);
        }

        if (bookCreateDTO.getTitulo().isEmpty() || bookCreateDTO.getAutor().isEmpty()
                || bookCreateDTO.getEditorial().isEmpty() || bookCreateDTO.getCategoria().isEmpty()
                || bookCreateDTO.getFechaLanzamiento().isEmpty() || bookCreateDTO.getPrecio().isEmpty()
                || bookCreateDTO.getPortada().isEmpty() || bookCreateDTO.getResenia().isEmpty()){
            return new ResponseEntity<>("Campos sin rellenar",HttpStatus.FORBIDDEN);
        }

        if(fechaLanzamiento.isAfter(LocalDate.now())){
            return new ResponseEntity<>("Fecha de Lanzamiento inválida", HttpStatus.FORBIDDEN);
        }

        book.setTitulo(bookCreateDTO.getTitulo());
        book.setAutor(bookCreateDTO.getAutor());
        book.setEditorial(bookCreateDTO.getEditorial());
        book.setCategoria(bookCreateDTO.getCategoria());
        book.setPrecio(price);
        book.setFechaLanzamiento(fechaLanzamiento);
        book.setResenia(bookCreateDTO.getResenia());
        bookService.save(book);

        return new ResponseEntity<>("Libro editado",HttpStatus.CREATED);
    }

    //user add book to library
    @PatchMapping("/api/books/addLibrary/edit")
    public ResponseEntity<Object> addLibrary( @RequestParam String bookId) {

        Long id = Long.parseLong(bookId);
        Book book = bookService.getById(id).orElse(null);

        if (bookId.isEmpty()) {
            return new ResponseEntity<>("El campo no puede estar vacío", HttpStatus.BAD_REQUEST);
        }

        if(book.getType() == BookType.NOFAV){
            book.setType(BookType.FAV);
            bookService.save(book);
            return new ResponseEntity<>("Añadido a la biblioteca", HttpStatus.OK);
        }

        if(book.getType() == BookType.FAV){
            book.setType(BookType.NOFAV);
            bookService.save(book);
            return new ResponseEntity<>("Movido a 'General'", HttpStatus.OK);
        }
        return new ResponseEntity<>("Libro añadido exitosamente",HttpStatus.OK);
    };



}
