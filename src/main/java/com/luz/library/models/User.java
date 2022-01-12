package com.luz.library.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole role;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    //@Singular
    @ToString.Exclude
    @JsonIgnore
    List<UserBook> userBooks;

    public Map<String, Object> userMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("fisrtName", this.getFirstName());
        map.put("lastName", this.getLastName());
        map.put("email", this.getEmail());
        map.put("password", this.getPassword());
        map.put("userRole", this.getRole());
        map.put("userbooks", this.getUserBooks());

        return map;
    }
}
