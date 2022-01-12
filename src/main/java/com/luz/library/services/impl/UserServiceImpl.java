package com.luz.library.services.impl;


import com.luz.library.dtos.UserDTO;
import com.luz.library.models.User;
import com.luz.library.models.UserRole;
import com.luz.library.repositories.UserRepository;
import com.luz.library.services.UserService;
import com.luz.library.services.specifications.UserSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Page<User> getUserByFilter(UserDTO filter, Pageable pageable) {
        return userRepository.findAll(UserSpecifications.userFilter(filter), pageable);
    }

    @Override
    public void saveUser(String firstName, String lastName, String email, String password, UserRole role) {
        userRepository.save(User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .role(role)
                .build());
    }



}
