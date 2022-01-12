package com.luz.library.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luz.library.models.User;
import com.luz.library.models.UserBook;
import com.luz.library.models.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole role;
    //@JsonIgnore
    private List<UserBook> userbooks;


}
