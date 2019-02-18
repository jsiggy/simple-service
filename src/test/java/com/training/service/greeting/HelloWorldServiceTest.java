package com.training.service.greeting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloWorldServiceTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/hello-world",
                String.class)).contains("Hello World");
    }

    @Test
    public void greetingShouldReturnBean() {
        final HelloWorldBean helloWorldBean = this.restTemplate.getForObject("http://localhost:" + port + "/hello-world-bean",
                HelloWorldBean.class);
        assertThat(helloWorldBean.getMessage()).isEqualTo("Hi, John");
    }

    @Test
    public void greetingShouldReturnMessageWithUserSpecifiedName() {
        final HelloWorldBean helloWorldBean = this.restTemplate.getForObject("http://localhost:" + port + "/hello-world-name/FooBar",
                HelloWorldBean.class);
        assertThat(helloWorldBean.getMessage()).isEqualTo("Hello FooBar");
    }
}
