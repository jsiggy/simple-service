package com.training.service.users;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static com.training.service.users.UserTestUtil.createBirthdate;
import static java.util.Calendar.JANUARY;
import static java.util.Calendar.MARCH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock UserRepository userRepository;

    private User newton;
    private User einstein;

    @Before
    public void setUp() {
        newton = new User.UserBuilder().id(1l)
                .name("Isaac Newton")
                .birthdate(createBirthdate(1643, JANUARY, 4)).build();
        einstein = new User.UserBuilder()
                .name("Albert Einstein")
                .birthdate(createBirthdate(1879, MARCH, 14)).build();

    }

    @Test
    public void retrieveAllShouldUseUserRepositoryFindAll() {
        final UserController userController = new UserController(userRepository);
        when(userRepository.findAll()).thenReturn(Arrays.asList(newton, einstein));

        userController.retrieveAllUsers();

        verify(userRepository).findAll();
    }

    @Test
    public void retrieveAllShouldGetAllUsersInTheRepository() {
        final UserController userController = new UserController(userRepository);
        when(userRepository.findAll()).thenReturn(Arrays.asList(newton, einstein));

        final List<User> users = userController.retrieveAllUsers();

        assertThat(users.size()).isEqualTo(2);
    }

    @Test
    public void retrieveAllShouldNotFailWhenZeroUsers() {
        final UserController userController = new UserController(userRepository);

        final List<User> users = userController.retrieveAllUsers();

        assertThat(users.size()).isEqualTo(0);
    }

    @Test
    public void retrieveUserShouldUseUserRepositoryFind() {
        long id = newton.getId();
        final UserController userController = new UserController(userRepository);
        when(userRepository.find(id)).thenReturn(newton);

        userController.retrieveUser(id);

        verify(userRepository).find(id);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowUserNotFoundWhenNoSuchUserId() {
        long id = newton.getId();
        final UserController userController = new UserController(userRepository);
        when(userRepository.find(id)).thenReturn(null);

        userController.retrieveUser(id);
    }

    @Test
    @Ignore("Need to mock the static methods that find the HttpRequest of createUser")
    public void shouldAskUserRepoToSaveAUser() {
        final UserController userController = new UserController(userRepository);
        when(userRepository.save(newton)).thenReturn(newton);
        // need to mock

        userController.createUser(newton);

        verify(userRepository).save(newton);
    }

    @Test
    public void shouldUseUserRepositoryToDeleteAnExistingUser() {
        long id = newton.getId();
        final UserController userController = new UserController(userRepository);
        when(userRepository.remove(id)).thenReturn(true);

        userController.deleteUser(id);

        verify(userRepository).remove(id);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowUserNotFoundWhenTryingtoDeleteANonExistingUser() {
        long id = newton.getId();
        final UserController userController = new UserController(userRepository);
        when(userRepository.remove(id)).thenReturn(false);

        userController.deleteUser(id);
    }
}