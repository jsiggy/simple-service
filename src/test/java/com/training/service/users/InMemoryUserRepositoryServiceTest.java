package com.training.service.users;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.training.service.users.UserTestUtil.createBirthdate;
import static java.util.Calendar.*;
import static org.junit.Assert.*;

public class InMemoryUserRepositoryServiceTest {

    private UserRepository userRepository;
    private User bruce;
    private User chuck;
    private User charles;
    private User einstein;

    @Before
    public void setUp() {
        bruce = new User.UserBuilder().name("Bruce Lee")
                .birthdate(createBirthdate(1940, NOVEMBER, 27)).build();
        chuck = new User.UserBuilder().name("Chuck Norris")
                .birthdate(createBirthdate(1940, MARCH, 10)).build();
        charles = new User.UserBuilder().name("Charles Darwin")
                .birthdate(createBirthdate(1882, APRIL, 19)).build();
        einstein = new User.UserBuilder().name("Albert Einstein")
                .birthdate(createBirthdate(1879, MARCH, 14)).build();

        userRepository = new InMemoryUserRepositoryService();
    }

    @Test
    public void shouldInitializeAsEmpty() {
        assertEquals(0, userRepository.size());
    }

    @Test
    public void canSaveAUser() {
        User actualNewUser = userRepository.save(bruce);

        assertEquals(bruce, actualNewUser);
    }

    @Test
    public void canSaveMultipleUsers() {
        addSomeUsers();

        assertEquals(4, userRepository.size());
    }

    @Test
    public void shouldCreateNewUserWithAnId() {
        assertNull(bruce.getId());

        User actualNewUser = userRepository.save(bruce);

        assertNotNull(actualNewUser.getId());
    }

    @Test
    public void canRetrieveAllUsers() {
        addSomeUsers();

        final List<User> users = userRepository.findAll();

        assertEquals(4, users.size());
        assertEquals("Bruce Lee", users.get(0).getName());
        assertEquals("Chuck Norris", users.get(1).getName());
        assertEquals("Charles Darwin", users.get(2).getName());
        assertEquals("Albert Einstein", users.get(3).getName());
    }

    @Test
    public void canRetrieveAUser() {
        addSomeUsers();

        final User actualUser = userRepository.find(2);

        assertEquals("Chuck Norris", actualUser.getName());
    }

    @Test
    public void canDeleteAUser() {
        addSomeUsers();

        userRepository.remove(2);

        assertEquals(3, userRepository.size());
        assertNull(userRepository.find(2));
    }

    @Test
    public void shouldReturnTrueIfUserCanBeRemoved() {
        userRepository.save(bruce);

        final boolean wasUserRemoved = userRepository.remove(1);

        assertTrue(wasUserRemoved);
    }

    @Test
    public void shouldReturnFalseIfUserCannotBeRemoved() {
        final boolean wasUserRemoved = userRepository.remove(10);

        assertFalse(wasUserRemoved);
    }

    @Test
    public void shouldIncreaseSizeWhenAUserIsAdded() {
        long initialSize = userRepository.size();

        userRepository.save(bruce);

        assertEquals(initialSize+1, userRepository.size());
    }

    @Test
    public void shouldDecreaseSizeWhenAUserIsRemoved() {
        userRepository.save(bruce);
        userRepository.save(chuck);
        userRepository.save(charles);
        userRepository.save(einstein);
        long initialSize = userRepository.size();

        userRepository.remove(2);

        assertEquals(initialSize-1, userRepository.size());
    }

    private void addSomeUsers() {
        userRepository.save(bruce);
        userRepository.save(chuck);
        userRepository.save(charles);
        userRepository.save(einstein);
    }
}
