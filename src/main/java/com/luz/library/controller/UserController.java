package com.luz.library.controller;

import com.luz.library.dtos.UserDTO;
import com.luz.library.models.User;
import com.luz.library.models.UserRole;
import com.luz.library.services.impl.BookServiceImpl;
import com.luz.library.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/api/users")
    public List<UserDTO> getUsers() {
        return userService.getAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }


    @GetMapping("/api/users/current")
    public UserDTO getCurrentUser(Authentication authentication) {
        return new UserDTO(userService.getByEmail(authentication.getName()));
    }

    @PostMapping("/api/users")
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("El formulario de registro tiene campos vacíos", HttpStatus.BAD_REQUEST);
        }

        if (userService.getByEmail(email) != null) {
            return new ResponseEntity<>("Ya existe un usuario con el email ingresado. Intentalo de nuevo.", HttpStatus.FORBIDDEN);
        }

        User user = userService.save(new User(firstName, lastName, email, passwordEncoder.encode(password), UserRole.USER));
        userService.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
