package com.training.service;

import com.training.service.users.UserController;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleServiceTest {

	@Autowired UserController userController;

	@Test
	public void contextLoads() {
		Assertions.assertThat(userController).isNotNull();
	}
}
