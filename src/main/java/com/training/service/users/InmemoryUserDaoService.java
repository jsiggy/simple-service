package com.training.service.users;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InmemoryUserDaoService {

    private final List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return users;
    }

    public User find(int id) {
        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(users.size() + 1);
        }
        users.add(user);
        return user;
    }

    public boolean remove(int id) {
        return users.removeIf(u -> u.getId() == id);
    }

    public int size() {
        return users.size();
    }
}
