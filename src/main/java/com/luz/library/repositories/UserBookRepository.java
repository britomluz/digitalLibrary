package com.luz.library.repositories;

import com.luz.library.models.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserBookRepository extends JpaRepository<UserBook, Long> {
}
