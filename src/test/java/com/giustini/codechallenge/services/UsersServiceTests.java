package com.giustini.codechallenge.services;

import com.giustini.codechallenge.exceptions.UserNotFoundException;
import com.giustini.codechallenge.models.Address;
import com.giustini.codechallenge.models.User;
import com.giustini.codechallenge.repositories.UsersRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static com.giustini.codechallenge.models.builders.AddressBuilder.anAddressBuilder;
import static com.giustini.codechallenge.models.builders.UserBuilder.aUserBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class UsersServiceTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UsersRepository usersRepository;

    private UsersService usersService;


    @Before
    public void init() {
        usersService = new UsersService();
        usersService.usersRepository = usersRepository;
    }

    @After
    public void clear() {
        testEntityManager.clear();
    }


    @Test
    public void shouldGetAllUsers() {

        User addedUser = testEntityManager.merge(buildUser());

        List<User> foundUsers = usersService.getUsers();

        assertThat(foundUsers.size(), is(1));
        checkSameUser(addedUser, foundUsers.get(0));
    }

    @Test
    public void shouldGetUserById() throws UserNotFoundException {

        User addedUser = testEntityManager.merge(buildUser());
        User foundUser = usersService.getUserById(addedUser.getId());

        checkSameUser(addedUser, foundUser);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowUserNotFoundExceptionWhenGetUserById() throws UserNotFoundException {

        User foundUser = usersService.getUserById(1);
    }

    @Test
    public void shouldUpdateUserById() throws UserNotFoundException {

        User addedUser = testEntityManager.merge(buildUser());

        addedUser.setName("Marge Simpson");
        addedUser.setEmail("marge@simpson.com");
        addedUser.setBirthDate(LocalDateTime.parse("1956-01-12T00:00"));

        User updatedUser = usersService.updateUserById(addedUser.getId(), addedUser);

        checkSameUser(addedUser, updatedUser);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowUserNotFoundExceptionWhenUpdateUserById() throws UserNotFoundException {

        User foundUser = usersService.updateUserById(1, buildUser());
    }

    @Test
    public void shouldDeleteUserById() throws UserNotFoundException {

        User addedUser = testEntityManager.merge(buildUser());

        usersService.deleteUserById(addedUser.getId());

        List<User> foundUsers = usersService.getUsers();

        assertThat(foundUsers.size(), is(0));
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowUserNotFoundExceptionDeleteUserById() throws UserNotFoundException {

        usersService.deleteUserById(1);
    }

    private void checkSameUser(User user, User target) {
        assertThat(user.getId(), is(target.getId()));
        assertThat(user.getName(), is(target.getName()));
        assertThat(user.getEmail(), is(target.getEmail()));
        assertThat(user.getBirthDate(), is(target.getBirthDate()));

        Address userAddress = user.getAddress();
        Address targetAddress = target.getAddress();

        checkSameAddress(userAddress, targetAddress);
    }

    private void checkSameAddress(Address address, Address target) {
        assertThat(address.getId(), is(target.getId()));
        assertThat(address.getStreet(), is(target.getStreet()));
        assertThat(address.getState(), is(target.getState()));
        assertThat(address.getCity(), is(target.getCity()));
        assertThat(address.getCountry(), is(target.getCountry()));
        assertThat(address.getZip(), is(target.getZip()));
    }

    private User buildUser() {
        return aUserBuilder()
                .withName("Homer Simpson")
                .withEmail("homer@simpson.com")
                .withBirthDate(LocalDateTime.parse("1956-03-12T00:00"))
                .withAddress(buildAddress())
                .build();

    }

    private Address buildAddress() {
        return anAddressBuilder()
                .withStreet("Evergreen Terrace, 742")
                .withState("Springfield")
                .withCity("Springfield")
                .withCountry("USA")
                .withZip("01020")
                .build();
    }

}
