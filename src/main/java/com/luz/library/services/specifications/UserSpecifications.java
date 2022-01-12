package com.luz.library.services.specifications;

import com.luz.library.dtos.BookDTO;
import com.luz.library.dtos.UserDTO;
import com.luz.library.models.Book;
import com.luz.library.models.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserSpecifications {


    public static Specification<User> userFilter(UserDTO filter){
        return (root, query, criteriaBuilder) -> {
           List<Predicate> criteriaList = new ArrayList<>();

           if(StringUtils.hasText(filter.getEmail())){
               criteriaList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("email")), "%"+filter.getEmail().toUpperCase(Locale.ROOT)+"%"));
           }

           return criteriaBuilder.and(criteriaList.toArray(new Predicate[criteriaList.size()]));
        };
    }
}
