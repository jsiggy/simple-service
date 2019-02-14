package com.training.myservice.users;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class InmemoryUserDaoService {

    private final List<User> users = new ArrayList<>();

    public InmemoryUserDaoService() {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3, "Jack", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(users.size()+1);
        }
        users.add(user);
        return user;
    }

    public void remove(int id) {
        for (int i = 0; i < users.size(); i++) {
            User user =  users.get(i);
            if (user.getId() == id)
                users.remove(i);
        }
    }
}
