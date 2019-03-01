package com.training.service.users;

import lombok.AccessLevel;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Setter(AccessLevel.PACKAGE)
    private LocationUriCreator uriCreator = new LocationUriCreator();

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable long id) {
        final User user = userRepository.find(id);
        if (user == null)
            throw new UserNotFoundException("id: " + id);
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        final User newUser = userRepository.save(user);

        final URI location = uriCreator.getUserLocationUri(newUser);
        return ResponseEntity.created(location).build();
    }

//    @Secured("admin")
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        final boolean removed = userRepository.remove(id);
        if (!removed)
            throw new UserNotFoundException("id: " + id);
    }
}
