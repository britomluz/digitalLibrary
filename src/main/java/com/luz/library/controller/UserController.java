package com.luz.library.controller;


import com.luz.library.dtos.UserDTO;

import com.luz.library.models.User;
import com.luz.library.models.UserRole;
import com.luz.library.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/api/users")
    public ResponseEntity<Object> getUsers(@RequestParam MultiValueMap<String, String> queryMap,
                                            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        try{
            UserDTO filter = UserDTO.builder()
                    .email(queryMap.getFirst("filter[email]"))
                    .build();

            Page<User> user = userService.getUserByFilter(filter, pageable);

            Map<String, Object> users = new HashMap<>();
            users.put("totalUsers", user.getTotalElements());
            users.put("totalPages", user.getTotalPages());
            users.put("numberPage", user.getNumber());
            users.put("size", user.getSize());
            users.put("sort", user.getSort());
            users.put("content", user.getContent());
            //users.put("rows", user.getContent().stream().map(User::userMap));

            return ResponseEntity.ok(users);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
        }

    }
    /*
    @GetMapping("/api/users/current")
    public UserDTO getCurrentUser(Authentication authentication) {
        return new UserDTO(userService.getByEmail(authentication.getName()));
    }*/
    @GetMapping("/api/users/current")
    public ResponseEntity<?> getUser(Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());

        if(user == null) {
            return new ResponseEntity<>("El usuario no esta logueado", HttpStatus.NOT_FOUND);
        }

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .userbooks(user.getUserBooks())
                .build();

        return ResponseEntity.ok(userDTO);
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

        userService.saveUser(firstName, lastName, email, passwordEncoder.encode(password), UserRole.USER);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
