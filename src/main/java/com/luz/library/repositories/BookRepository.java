package com.luz.library.repositories;
import com.luz.library.models.Book;
import com.luz.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitulo(String titulo);
}
