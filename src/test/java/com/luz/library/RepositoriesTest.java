package com.luz.library;

import com.luz.library.models.*;
import com.luz.library.services.impl.BookServiceImpl;
import com.luz.library.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class RepositoriesTest {


        @Autowired
        UserServiceImpl userService;

        @Autowired
        BookServiceImpl bookService;

        @Autowired
        PasswordEncoder passwordEncoder;

        @Test
        public void should_create_new_user() {
            User user = User.builder()
                    .firstName("Tom")
                    .lastName("Crus")
                    .email("tom@gmail.com")
                    .password(passwordEncoder.encode("tom123"))
                    .role(UserRole.ADMIN)
                    .build();
            userService.save(user);

            User userByEmail = userService.getByEmail("tom@gmail.com");
            assertThat(userByEmail).isNotNull();


            System.out.println(userByEmail.toString());
        }

        @Test
        public void should_find_all_books() {
            List<Book> books = bookService.getAll();
          //  assertThat(books).hasSize(20);

            System.out.println("Obtuviste la lista de libros");
            System.out.println(books.size());
        }

        @Test
        public void should_add_book() {
            Book book = Book.builder()
                    .titulo("LAS CRONICAS DE NARNIA".toUpperCase())
                    .autor("Madeleine Kaskov")
                    .editorial("Planeta")
                    .categoria("Fantasía")
                    .precio(143D)
                    .fechaLanzamiento(LocalDate.parse("2015-03-08"))
                    .portada("https://res.cloudinary.com/luz-brito/image/upload/v1641250976/library/ladrona-de-libros_fwcj5j.jpg")
                    .resenia("Una novela preciosa, tremendamente humana y emocionante, que describe las peripecias de una niña alemana de nueve años desde que es dada en adopción")
                    .bestSeller(Boolean.FALSE)
                    .build();
            bookService.save(book);


            Book bookByTiulo = bookService.getByTitulo("LAS CRONICAS DE NARNIA");
            assertThat(bookByTiulo).isNotNull();

            List<Book> books = bookService.getAll();
          //  assertThat(books).hasSize(20);

            System.out.println("Se agrego el libro");
            System.out.println(books.size());
        }

        @Test
        public void should_delete_book() {
            Book bookByTiulo = bookService.getByTitulo("RAYUELA");
            assertThat(bookByTiulo).isNotNull();

            bookService.delete(bookByTiulo);

            List<Book> books = bookService.getAll();
            // assertThat(books).hasSize(19);

            System.out.println("Se borro el libro");
            System.out.println(books.size());
        }

        @Test
        public void should_edit_book() {
            Book bookByTiulo = bookService.getByTitulo("LAS CRONICAS DE NARNIA");
            assertThat(bookByTiulo).isNotNull();

            bookByTiulo.setPrecio(188D);
            bookService.save(bookByTiulo);

            System.out.println("Se edito el libro");
        }


    }

