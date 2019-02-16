package com.training.service.users;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.*;
import static org.junit.Assert.*;

public class InmemoryUserRepositoryServiceTest {

    private InmemoryUserRepositoryService userDaoService;
    private User bruce;
    private User chuck;
    private User charles;

    @Before
    public void setUp() {
        bruce = new User("Bruce Lee", createBirthdate(1940, NOVEMBER, 27));
        chuck = new User("Chuck Norris", createBirthdate(1940, MARCH, 10));
        charles = new User("Charles Darwin", createBirthdate(1882, APRIL, 19));
        userDaoService = new InmemoryUserRepositoryService();
    }

    @Test
    public void shouldInitializeAsEmpty() {
        assertEquals(0, userDaoService.size());
    }

    @Test
    public void canSaveAUser() {
        User actualNewUser = userDaoService.save(bruce);

        assertEquals(bruce, actualNewUser);
    }

    @Test
    public void canSaveMultipleUsers() {
        addSomeUsers();

        assertEquals(3, userDaoService.size());
    }

    @Test
    public void shouldCreateNewUserWithAnId() {
        assertNull(bruce.getId());

        User actualNewUser = userDaoService.save(bruce);

        assertNotNull(actualNewUser.getId());
    }

    @Test
    public void canRetrieveAllUsers() {
        addSomeUsers();

        final List<User> users = userDaoService.findAll();

        assertEquals(3, users.size());
        assertEquals("Bruce Lee", users.get(0).getName());
        assertEquals("Chuck Norris", users.get(1).getName());
        assertEquals("Charles Darwin", users.get(2).getName());
    }

    @Test
    public void canRetrieveAUser() {
        addSomeUsers();

        final User actualUser = userDaoService.find(2);

        assertEquals("Chuck Norris", actualUser.getName());
    }

    @Test
    public void canDeleteAUser() {
        addSomeUsers();

        userDaoService.remove(1);

        assertEquals(2, userDaoService.size());
        assertNull(userDaoService.find(1));
    }

    @Test
    public void shouldReturnTrueIfUserCanBeRemoved() {
        userDaoService.save(bruce);

        final boolean wasUserRemoved = userDaoService.remove(1);

        assertTrue(wasUserRemoved);
    }

    @Test
    public void shouldReturnFalseIfUserCannotBeRemoved() {
        final boolean wasUserRemoved = userDaoService.remove(10);

        assertFalse(wasUserRemoved);
    }

    private void addSomeUsers() {
        userDaoService.save(bruce);
        userDaoService.save(chuck);
        userDaoService.save(charles);
    }

    private Date createBirthdate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
