package com.training.myservice.users;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class InmemoryUserDaoServiceTest {

    @Test
    public void canRetrieveAllUsers() {
        assertEquals(3, new InmemoryUserDaoService().findAll().size());
    }

    @Test
    public void canRetrieveAUser() {
        assertEquals("Eve", new InmemoryUserDaoService().findOne(2).getName());
    }

    @Test
    public void canSaveAUser() {
        Date birthdate = new Date();
        User newUser = new User("John", birthdate);

        User actualNewUser = new InmemoryUserDaoService().save(newUser);

        assertEquals(newUser, actualNewUser);
    }

    @Test
    public void canDeleteAUser() {
    }
}
