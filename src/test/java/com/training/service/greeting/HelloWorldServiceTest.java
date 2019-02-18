package com.training.service.greeting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloWorldServiceTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        final ResponseEntity<String> responseEntity =
                this.restTemplate.getForEntity("http://localhost:" + port + "/hello-world", String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains("Hello World");
    }

    @Test
    public void greetingShouldReturnHelloWorldBean() {
        final ResponseEntity<HelloWorldBean> responseEntity =
                this.restTemplate.getForEntity("http://localhost:" + port + "/hello-world-bean", HelloWorldBean.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(APPLICATION_JSON_UTF8);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getMessage()).contains("Hi, John");
    }

    @Test
    public void greetingShouldReturnMessageWithUserSpecifiedName() {
        final ResponseEntity<HelloWorldBean> responseEntity =
                this.restTemplate.getForEntity("http://localhost:" + port + "/hello-world-name/FooBar",
                        HelloWorldBean.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(APPLICATION_JSON_UTF8);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getMessage()).contains("Hello FooBar");
    }
}
