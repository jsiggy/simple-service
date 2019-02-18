package com.training.service;

import com.training.service.greeting.HelloWorldController;
import com.training.service.users.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleServiceSmokeTest {

	@Autowired UserController userController;
	@Autowired HelloWorldController helloWorldController;

	@Test
	public void contextLoads() {
		assertThat(helloWorldController).isNotNull();
		assertThat(userController).isNotNull();
	}
}
