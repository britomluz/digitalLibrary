package com.luz.library.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookCreateDTO {
    private String titulo;
    private String autor;
    private String editorial;
    private String categoria;
    private String precio;
    private String fechaLanzamiento;
    private String portada;
    private String resenia;

}
