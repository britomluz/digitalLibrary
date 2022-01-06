package com.luz.library;

import com.luz.library.models.*;
import com.luz.library.services.impl.BookServiceImpl;
import com.luz.library.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class RepositoriesTest {


        @Autowired
        UserServiceImpl userService;

        @Autowired
        BookServiceImpl bookService;


        @Test
        public void should_create_new_user() {
            User user = new User("tom","crus", "tom@gmail.com", "tom123", UserRole.ADMIN);
            userService.save(user);

            User userByEmail = userService.getByEmail("tom@gmail.com");
            assertThat(userByEmail).isNotNull();


            System.out.println(userByEmail.toString());
        }

        @Test
        public void should_find_all_books() {
            List<Book> books = bookService.getAll();
            assertThat(books).hasSize(20);
        }

        @Test
        public void should_delete_book() {
            Book bookByTiulo = bookService.findByTitulo("RAYUELA");
            assertThat(bookByTiulo).isNotNull();

            bookService.delete(bookByTiulo);

            List<Book> books = bookService.getAll();
            assertThat(books).hasSize(19);
        }

        @Test
        public void should_add_book() {
            Book book = new Book("LAS CRONICAS DE NARNIA", "Madeleine Kaskov", "Planeta", "Fantasía", 143D, LocalDate.parse("2015-03-08"),"https://res.cloudinary.com/luz-brito/image/upload/v1641250976/library/ladrona-de-libros_fwcj5j.jpg", "Una novela preciosa, tremendamente humana y emocionante, que describe las peripecias de una niña alemana de nueve años desde que es dada en adopción", BookType.FAV, BookBestSeller.YES);
            bookService.save(book);


            Book bookByTiulo = bookService.findByTitulo("LAS CRONICAS DE NARNIA");
            assertThat(bookByTiulo).isNotNull();

            List<Book> books = bookService.getAll();
            assertThat(books).hasSize(20);
        }

        @Test
        public void should_edit_book() {
            Book bookByTiulo = bookService.findByTitulo("LAS CRONICAS DE NARNIA");
            assertThat(bookByTiulo).isNotNull();

            bookByTiulo.setPrecio(188D);
            bookService.save(bookByTiulo);
        }


    }

