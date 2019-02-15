package com.training.myservice.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private InmemoryUserDaoService userService;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return userService.find(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        userService.save(user);
    }
}
