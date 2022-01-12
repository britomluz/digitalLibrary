package com.luz.library.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
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


    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    //@Singular
    @ToString.Exclude
    @JsonIgnore
    List<UserBook> userBooks;

    /*
    public Map<String, Object> bookMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("titulo", this.getTitulo());
        map.put("autor", this.getAutor());
        map.put("editorial", this.getEditorial());
        map.put("categoria", this.getCategoria());
        map.put("precio", this.getPrecio());
        map.put("fechaLanzamiento", this.getFechaLanzamiento());
        map.put("portada", this.getPortada());
        map.put("resenia", this.getResenia());
        map.put("bestSeller", this.getBestSeller());

        return map;
        }*/



}
