package com.training.service.greeting;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.TEXT;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloWorldServiceRestAssuredIT {
    @LocalServerPort
    private int port;

    @Before
    public void setBaseUri() {
        RestAssured.port = port;
        baseURI = "http://localhost";

    }

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        get("/hello-world")
                .then()
                .statusCode(200)
                .contentType(TEXT)
                .body(containsString("Hello World"));
    }

    @Test
    public void greetingShouldReturnHelloWorldBean() throws Exception {
        get("/hello-world-bean")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("message", Matchers.equalTo("Hi, John"));
    }

    @Test
    public void greetingShouldReturnShouldReturnMessageWithUserSpecifiedName() throws Exception {
        get("/hello-world-name/FooBar")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("message", Matchers.equalTo("Hello FooBar"));
    }
}
