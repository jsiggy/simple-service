package com.training.service.users;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User find(long id);

    User save(User user);

    boolean remove(long id);

    long size();
}
