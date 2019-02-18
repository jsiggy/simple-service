package com.training.service.users;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InmemoryUserRepositoryService implements UserRepository {

    private final List<User> users = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User find(long id) {
        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(counter.incrementAndGet());
        }
        users.add(user);
        return user;
    }

    @Override
    public boolean remove(long id) {
        return users.removeIf(u -> u.getId() == id);
    }

    @Override
    public long size() {
        return users.size();
    }
}
