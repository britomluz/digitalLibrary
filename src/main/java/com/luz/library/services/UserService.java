package com.luz.library.services;

import com.luz.library.models.Book;
import com.luz.library.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User save(User user);
    public List<User> getAll();
    public void delete(User user);
    public Optional<User> getById(Long id);
    public User getByEmail(String email);
}
