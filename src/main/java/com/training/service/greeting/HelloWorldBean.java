package com.training.service.greeting;

import lombok.*;

@NoArgsConstructor @EqualsAndHashCode @ToString
public class HelloWorldBean {
    @Getter @Setter
    private String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }
}
