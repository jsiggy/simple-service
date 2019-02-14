package com.training.myservice.greeting;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode @ToString
public class HelloWorldBean {
    private String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }
}
