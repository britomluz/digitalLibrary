package com.luz.library.services.specifications;

import com.luz.library.dtos.BookDTO;
import com.luz.library.models.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BookSpecifications {


    public static Specification<Book> bookFilter(BookDTO filter){
        return (root, query, criteriaBuilder) -> {
           List<Predicate> criteriaList = new ArrayList<>();

           if(StringUtils.hasText(filter.getTitulo())){
               criteriaList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("titulo")), "%"+filter.getTitulo().toUpperCase(Locale.ROOT)+"%"));
           }

           if(StringUtils.hasText(filter.getAutor())){
               criteriaList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("autor")), "%"+filter.getAutor().toUpperCase(Locale.ROOT)+"%"));
           }

           if(StringUtils.hasText(filter.getCategoria())){
               criteriaList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("categoria")), "%"+filter.getCategoria().toUpperCase(Locale.ROOT)+"%"));
           }
            if(StringUtils.hasText(filter.getEditorial())){
                criteriaList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("editorial")), "%"+filter.getEditorial().toUpperCase(Locale.ROOT)+"%"));
            }

           if(filter.getPrecio() != null){
               criteriaList.add(criteriaBuilder.lessThanOrEqualTo(root.get("precio"), filter.getPrecio()));
           }



           return criteriaBuilder.and(criteriaList.toArray(new Predicate[criteriaList.size()]));
        };
    }
}
