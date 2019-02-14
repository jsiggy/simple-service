package com.training.myservice.greeting;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode @ToString
public class HelloWorldBean {
    @Getter
    private String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }
}
