package com.luz.library.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luz.library.models.Book;
import com.luz.library.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserBookDTO {
    private Long id;
    private String titulo;
    private String autor;
}
