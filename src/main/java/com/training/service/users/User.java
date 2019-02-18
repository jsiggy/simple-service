package com.training.service.users;

import lombok.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor @EqualsAndHashCode @ToString @Builder
public class User {

    @Getter @Setter(AccessLevel.PACKAGE)
    private Long id;

    @Size(min = 2) @Getter @Setter
    private String name;

    @Past @Getter @Setter
    private Date birthdate;

    public User(String name, Date birthdate) {
        this(null, name, birthdate);
    }

    User(Long id, String name, Date birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }
}
