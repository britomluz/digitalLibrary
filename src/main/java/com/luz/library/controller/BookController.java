package com.luz.library.controller;


import com.luz.library.dtos.BookCreateDTO;
import com.luz.library.dtos.BookDTO;
import com.luz.library.models.*;
import com.luz.library.repositories.UserBookRepository;
import com.luz.library.services.impl.BookServiceImpl;
import com.luz.library.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;

@RestController
public class BookController {

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserBookRepository userBookRepository;


    //get bookDTO list with filters and pagination

    @GetMapping("/api/books")
    public ResponseEntity<Object> showBooks(@RequestParam MultiValueMap<String, String> queryMap,
                                            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

            try{
                BookDTO filter = BookDTO.builder()
                        .titulo(queryMap.getFirst("filter[titulo]"))
                        .autor(queryMap.getFirst("filter[autor]"))
                        .categoria(queryMap.getFirst("filter[categoria]"))
                        .editorial(queryMap.getFirst("filter[editorial]"))
                        .precio(queryMap.getFirst("filter[precio]") != null ? Double.parseDouble(Objects.requireNonNull(queryMap.getFirst("filter[precio]"))):null)
                        .build();

                Page<Book> page = bookService.getBookByFilter(filter, pageable);

                Map<String, Object> retMap = new HashMap<>();
                retMap.put("totalBooks", page.getTotalElements());
                retMap.put("totalPages", page.getTotalPages());
                retMap.put("numberPage", page.getNumber());
                retMap.put("size", page.getSize());
                retMap.put("sort", page.getSort());
                retMap.put("content", page.getContent());
                // retMap.put("rows", page.getContent().stream().map(Book::bookMap));

                return ResponseEntity.ok(retMap);
            } catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
            }

    };

    //view book details
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Object> getOrders(Authentication authentication, @PathVariable Long id){

        User user = userService.getByEmail(authentication.getName());
        Book book = bookService.getById(id).orElse(null);

        try{
            BookDTO bookDTO = BookDTO.builder()
                    .id(book.getId())
                    .titulo(book.getTitulo())
                    .autor(book.getAutor())
                    .categoria(book.getCategoria())
                    .editorial(book.getEditorial())
                    .precio(book.getPrecio())
                    .fechaLanzamiento(book.getFechaLanzamiento())
                    .portada(book.getPortada())
                    .resenia(book.getResenia())
                    .bestSeller(Boolean.FALSE)
                    .build();

            return ResponseEntity.ok(bookDTO);

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage(), e.getCause());
        }


    }

    //admin add book
    @PostMapping("/api/books")
    public ResponseEntity<?> createBook(Authentication authentication, @RequestBody BookCreateDTO bookCreateDTO){
        User user = userService.getByEmail(authentication.getName());

        if (!user.getRole().equals(UserRole.ADMIN)){
            return new ResponseEntity<>("Permisos insuficientes",HttpStatus.UNAUTHORIZED);
        }

        if (bookCreateDTO.getTitulo().isEmpty() || bookCreateDTO.getAutor().isEmpty()
            || bookCreateDTO.getEditorial().isEmpty() || bookCreateDTO.getCategoria().isEmpty()
            || bookCreateDTO.getFechaLanzamiento().isEmpty() || bookCreateDTO.getPrecio().isEmpty()
            || bookCreateDTO.getPortada().isEmpty() || bookCreateDTO.getResenia().isEmpty()){
            return new ResponseEntity<>("Campos sin rellenar",HttpStatus.FORBIDDEN);
        }

        Book book = bookService.getByTitulo(bookCreateDTO.getTitulo().toUpperCase());

        if (book != null){
            return new ResponseEntity<>("El libro ya existe",HttpStatus.FORBIDDEN);
        }

        Double price = Double.parseDouble(bookCreateDTO.getPrecio());
        LocalDate fechaLanzamiento = LocalDate.parse(bookCreateDTO.getFechaLanzamiento());

        if(fechaLanzamiento.isAfter(LocalDate.now())){
            return new ResponseEntity<>("Fecha de Lanzamiento inválida", HttpStatus.BAD_REQUEST);
        }

        bookService.saveBook(bookCreateDTO);
        return new ResponseEntity<>("Libro agregado",HttpStatus.CREATED);
    }

    //admin add book to bestSellers
        @PatchMapping("/api/books/bestseller")
    public ResponseEntity<Object> addBestSeller(Authentication authentication, @RequestParam String bookId) {

        User user = userService.getByEmail(authentication.getName());

        if (!user.getRole().equals(UserRole.ADMIN)){
            return new ResponseEntity<>("Permisos insuficientes",HttpStatus.FORBIDDEN);
        }

        if (bookId.isEmpty()) {
            return new ResponseEntity<>("El campo no puede estar vacío", HttpStatus.BAD_REQUEST);
        }

        Long id = Long.parseLong(bookId);
        Book book = bookService.getById(id).orElse(null);

        if(book.getBestSeller() == Boolean.FALSE){
            if (bookService.getAllByBestSellers(Boolean.TRUE).size() > 9) {
                return new ResponseEntity<>("No se puede agregar más de 10 libros a Best Sellers", HttpStatus.BAD_REQUEST);
            }
            book.setBestSeller(Boolean.TRUE);
            bookService.save(book);
            return new ResponseEntity<>("Movido a 'Best Sellers'", HttpStatus.OK);
        }

        if(book.getBestSeller() == Boolean.TRUE){
            book.setBestSeller(Boolean.FALSE);
            bookService.save(book);
            return new ResponseEntity<>("Movido a 'General'", HttpStatus.OK);
        }
        return new ResponseEntity<>("Libro editado exitosamente",HttpStatus.OK);
    };

    //admin delete books
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<?> deleteBook(Authentication authentication, @PathVariable("id") Long id){

        User user = userService.getByEmail(authentication.getName());
        if (!user.getRole().equals(UserRole.ADMIN)){
            return new ResponseEntity<>("Permisos insuficientes",HttpStatus.UNAUTHORIZED);
        }

        Book book = bookService.getById(id).orElse(null);
        if (book== null){
            return new ResponseEntity<>("El libro no existe",HttpStatus.FORBIDDEN);
        }

        bookService.delete(book);
        return new ResponseEntity<>("Libro borrado con éxito",HttpStatus.OK);
    }

    //admin edit book
    @PutMapping("/api/books/{id}")
    public ResponseEntity<?> editProduct(Authentication authentication, @PathVariable("id") Long id,
                                         @RequestBody BookCreateDTO bookCreateDTO){
        User user = userService.getByEmail(authentication.getName());


        if (!user.getRole().equals(UserRole.ADMIN)){
            return new ResponseEntity<>("Permisos insuficientes",HttpStatus.FORBIDDEN);
        }

        if (bookCreateDTO.getTitulo().isEmpty() || bookCreateDTO.getAutor().isEmpty()
                || bookCreateDTO.getEditorial().isEmpty() || bookCreateDTO.getCategoria().isEmpty()
                || bookCreateDTO.getFechaLanzamiento().isEmpty() || bookCreateDTO.getPrecio().isEmpty()
                || bookCreateDTO.getPortada().isEmpty() || bookCreateDTO.getResenia().isEmpty()){
            return new ResponseEntity<>("Campos sin rellenar",HttpStatus.BAD_REQUEST);
        }

        Book book = bookService.getById(id).orElse(null);
        LocalDate fechaLanzamiento = LocalDate.parse(bookCreateDTO.getFechaLanzamiento());

        if(fechaLanzamiento.isAfter(LocalDate.now())){
            return new ResponseEntity<>("Fecha de Lanzamiento inválida", HttpStatus.BAD_REQUEST);
        }

        Double price = Double.parseDouble(bookCreateDTO.getPrecio());

        book.setTitulo(bookCreateDTO.getTitulo());
        book.setAutor(bookCreateDTO.getAutor());
        book.setEditorial(bookCreateDTO.getEditorial());
        book.setCategoria(bookCreateDTO.getCategoria());
        book.setPrecio(price);
        book.setFechaLanzamiento(fechaLanzamiento);
        book.setResenia(bookCreateDTO.getResenia());
        bookService.save(book);

        return new ResponseEntity<>("Libro editado",HttpStatus.OK);
    }

    //user add book to library
    @PostMapping("/api/books/userbooks")
    public ResponseEntity<Object> addLibrary(Authentication authentication, @RequestParam String bookId) {

        User user = userService.getByEmail(authentication.getName());

        if (user == null){
            return new ResponseEntity<>("Permisos insuficientes",HttpStatus.FORBIDDEN);
        }

        if (bookId.isEmpty()) {
            return new ResponseEntity<>("El campo no puede estar vacío", HttpStatus.BAD_REQUEST);
        }

        Long id = Long.parseLong(bookId);
        Book book = bookService.getById(id).orElse(null);


        UserBook userbook = UserBook.builder()
                .titulo(book.getTitulo())
                .autor(book.getAutor())
                .user(user)
                .book(book)
                .build();
        userBookRepository.save(userbook);
        
       return ResponseEntity.ok(userbook);
    };



}


