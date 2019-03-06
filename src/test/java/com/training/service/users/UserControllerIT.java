package com.training.service.users;

import com.training.service.SimpleService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.training.service.users.UserTestUtil.createBirthdate;
import static java.util.Calendar.MARCH;
import static java.util.Calendar.NOVEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimpleService.class, webEnvironment = RANDOM_PORT)
public class UserControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private InMemoryUserRepositoryService userService;

    private User bruce_lee;
    private User albert_einstein;
    private List<User> expectedUsers;

    @Before
    public void setUp() throws Exception {
        bruce_lee = new User.UserBuilder().name("Bruce Lee")
                .birthdate(createBirthdate(1940, NOVEMBER, 27)).build();
        albert_einstein = new User.UserBuilder().name("Albert Einstein")
                .birthdate(createBirthdate(1879, MARCH, 14)).build();

        expectedUsers = new ArrayList<User>() { { add(bruce_lee); add(albert_einstein); }};

    }

    @After
    public void tearDown() throws Exception {
        userService.removeAll();
    }

    @Test
    public void emptyUsersReturnsEmptyList() {

        final ResponseEntity<User[]> responseEntity =
                this.restTemplate.getForEntity(getUrl("/users"), User[].class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(APPLICATION_JSON_UTF8);
        assertThat(responseEntity.getBody()).isEmpty();
    }

    @Test
    public void usersReturnsCorrectListOfUsers() {

        userService.save(bruce_lee);
        userService.save(albert_einstein);

        final ResponseEntity<User[]> responseEntity =
                this.restTemplate.getForEntity(getUrl("/users"), User[].class);

        assertThat(getListOfUsersFromBody(responseEntity)).isEqualTo(expectedUsers);
    }

    @Test
    public void nonNullUsersReturnsNonNullJSON() {

        userService.save(bruce_lee);
        userService.save(albert_einstein);

        final ResponseEntity<User[]> responseEntity =
                this.restTemplate.getForEntity(getUrl("/users"), User[].class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(APPLICATION_JSON_UTF8);
        assertThat(getListOfUsersFromBody(responseEntity)).isEqualTo(expectedUsers);
    }

    @Test
    public void userNotFoundFails() {

        final ResponseEntity<User> responseEntity =
                this.restTemplate.getForEntity(getUrl("/users/{id}"), User.class, Long.valueOf(1000l));

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void userFoundReturnsTheUser() {

        userService.save(bruce_lee);

        final ResponseEntity<User> responseEntity =
                this.restTemplate.getForEntity(getUrl("/users/{id}"), User.class, bruce_lee.getId());

        assertThat(responseEntity.getBody()).isEqualTo(bruce_lee);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private List<User> getListOfUsersFromBody(ResponseEntity<User[]> responseEntity) {
        return Arrays.asList(responseEntity.getBody());
    }

    private String getUrl(String resource) {
        return "http://localhost:" + port + resource;
    }
}
