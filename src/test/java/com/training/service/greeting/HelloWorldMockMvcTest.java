package com.training.service.greeting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloWorldController.class)
public class HelloWorldMockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/hello-world")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")));
    }

    @Test
    public void greetingShouldReturnShouldReturnHelloWorldBean() throws Exception {
        this.mockMvc.perform(get("/hello-world-bean")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{'message':'Hi, John'}"));
    }

    @Test
    public void greetingShouldReturnShouldReturnMessageWithUserSpecifiedName() throws Exception {
        this.mockMvc.perform(get("/hello-world-name/FooBar")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{'message':'Hello FooBar'}"));
    }
}
