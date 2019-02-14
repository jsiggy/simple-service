package com.training.myservice.greeting;

import org.junit.Test;

import static org.junit.Assert.*;

public class HelloWorldControllerTest {

    @Test
    public void helloWorldReturnsHelloWorldString() {
        assertEquals("Hello World", new HelloWorldController().helloWorld());
    }

    @Test
    public void helloWorldBeanReturnHelloWorldBean() {
        assertEquals(new HelloWorldBean("Hi, John"), new HelloWorldController().helloWorldBean());
    }
}