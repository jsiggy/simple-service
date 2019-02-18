package com.training.service.users;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URI;
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
    @Mock LocationUriCreator locationUriCreator;

    private User newton;
    private User einstein;
    private UserController userController;

    @Before
    public void setUp() {
        userController = new UserController(userRepository);
        userController.setUriCreator(locationUriCreator);

        newton = new User.UserBuilder().id(1l)
                .name("Isaac Newton")
                .birthdate(createBirthdate(1643, JANUARY, 4)).build();
        einstein = new User.UserBuilder()
                .name("Albert Einstein")
                .birthdate(createBirthdate(1879, MARCH, 14)).build();
    }

    @Test
    public void retrieveAllShouldUseUserRepositoryFindAll() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(newton, einstein));

        userController.retrieveAllUsers();

        verify(userRepository).findAll();
    }

    @Test
    public void retrieveAllShouldGetAllUsersInTheRepository() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(newton, einstein));

        final List<User> users = userController.retrieveAllUsers();

        assertThat(users.size()).isEqualTo(2);
    }

    @Test
    public void retrieveAllShouldNotFailWhenZeroUsers() {

        final List<User> users = userController.retrieveAllUsers();

        assertThat(users.size()).isEqualTo(0);
    }

    @Test
    public void retrieveUserShouldUseUserRepositoryFind() {
        long id = newton.getId();
        when(userRepository.find(id)).thenReturn(newton);

        userController.retrieveUser(id);

        verify(userRepository).find(id);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowUserNotFoundWhenNoSuchUserId() {
        long id = newton.getId();
        when(userRepository.find(id)).thenReturn(null);

        userController.retrieveUser(id);
    }

    @Test
    public void shouldAskUserRepoToSaveAUser() throws Exception {
        when(userRepository.save(newton)).thenReturn(newton);
        when(locationUriCreator.getUserLocationUri(newton)).thenReturn(new URI("http://localhost"));

        userController.createUser(newton);

        verify(userRepository).save(newton);
    }

    @Test
    public void shouldUseUserRepositoryToDeleteAnExistingUser() {
        long id = newton.getId();
        when(userRepository.remove(id)).thenReturn(true);

        userController.deleteUser(id);

        verify(userRepository).remove(id);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowUserNotFoundWhenTryingtoDeleteANonExistingUser() {
        long id = newton.getId();
        when(userRepository.remove(id)).thenReturn(false);

        userController.deleteUser(id);
    }
}