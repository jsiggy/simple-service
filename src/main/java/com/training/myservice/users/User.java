package com.training.myservice.users;

import lombok.*;

import java.util.Date;

@Getter @Setter @EqualsAndHashCode @ToString
public class User {
    private Integer id;
    private String name;
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
