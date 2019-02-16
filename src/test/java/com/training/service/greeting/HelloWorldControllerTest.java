package com.training.service.greeting;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloWorldControllerTest {

    @Test
    public void helloWorldReturnsHelloWorldString() {
        assertEquals("Hello World", new HelloWorldController().helloWorld());
    }

    @Test
    public void helloWorldBeanReturnHelloWorldBean() {
        assertEquals(new HelloWorldBean("Hi, John"), new HelloWorldController().helloWorldBean());
    }

    @Test
    public void helloWorldNameReturnsTheName() {
        assertEquals(new HelloWorldBean("Hello John"), new HelloWorldController().helloWorldPathVariableExample("John"));
    }
}