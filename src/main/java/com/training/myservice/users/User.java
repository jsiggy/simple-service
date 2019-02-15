package com.training.myservice.users;

import lombok.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor @Getter @Setter @EqualsAndHashCode @ToString @Builder
public class User {

    private Integer id;
    @Size(min = 2)
    private String name;
    @Past
    private Date birthdate;

    public User(String name, Date birthdate) {
        this(null, name, birthdate);
    }

    User(Integer id, String name, Date birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }
}
