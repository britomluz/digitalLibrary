package com.luz.library.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luz.library.models.UserBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {
    private Long id;
    private String titulo;
    private String autor;
    private String editorial;
    private String categoria;
    private Double precio;
    private LocalDate fechaLanzamiento;
    private String portada;
    private String resenia;
    private Boolean bestSeller;

    @JsonIgnore
    private List<UserBook> userbooks;


}
