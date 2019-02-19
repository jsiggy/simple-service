package com.training.service.greeting;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloWorldControllerTest {

    @Test
    public void shouldReturnHelloWorld() {
        assertEquals("Hello World", new HelloWorldController().helloWorld());
    }

    @Test
    public void shouldReturnHelloWorldBeansMessage() {
        assertEquals(new HelloWorldBean("Hi, John"), new HelloWorldController().helloWorldBean());
    }

    @Test
    public void shouldReturnPathVariableName() {
        assertEquals(new HelloWorldBean("Hello John"), new HelloWorldController().helloWorldPathVariableExample("John"));
    }
}