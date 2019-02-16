package com.training.service.greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hi, John");
    }

    @GetMapping(path = "/hello-world-name/{name}")
    public HelloWorldBean helloWorldPathVariableExample(@PathVariable(required = false) String name) {
        return new HelloWorldBean("Hello " + name);
    }
}
