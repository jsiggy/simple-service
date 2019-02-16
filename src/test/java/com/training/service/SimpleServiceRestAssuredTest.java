package com.training.service;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SimpleService.class)
@TestPropertySource(value = {"classpath:application.properties"})
@SpringBootTest(webEnvironment = DEFINED_PORT)
public class SimpleServiceRestAssuredTest {
    @Value("${server.port}")
    int port;

    @Before
    public void setBaseUri() {
        RestAssured.port = port;
        baseURI = "http://localhost";
    }

    @Test
    @Ignore
    public void canGetAllUsers() {
        get("/users")
                .then()
                .statusCode(200);
    }
}
