package com.luz.library.services;

import com.luz.library.dtos.UserDTO;
import com.luz.library.models.Book;
import com.luz.library.models.User;
import com.luz.library.models.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User save(User user);
    public List<User> getAll();
    public void delete(User user);
    public Optional<User> getById(Long id);
    public User getByEmail(String email);
    public void saveUser(String firstName, String lastName, String email, String password, UserRole role);
    public Page<User> getUserByFilter(UserDTO filter, Pageable pageable);
}
