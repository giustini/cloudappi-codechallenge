package com.giustini.codechallenge.models.builders;

import com.giustini.codechallenge.models.Address;
import com.giustini.codechallenge.models.User;

import java.time.LocalDateTime;

public class UserBuilder {

    private User user;

    public static UserBuilder anUserBuilder() {
        return new UserBuilder();
    }

    private UserBuilder() {
        this.user = new User();
    }

    public UserBuilder withId(Integer id) {
        user.setId(id);
        return this;
    }

    public UserBuilder withName(String name) {
        user.setName(name);
        return this;
    }

    public UserBuilder withEmail(String email) {
        user.setEmail(email);
        return this;
    }

    public UserBuilder withBirthDate(LocalDateTime birthDate) {
        user.setBirthDate(birthDate);
        return this;
    }

    public UserBuilder withAddress(Address address) {
        user.setAddress(address);
        return this;
    }

    public User build() {
        return user;
    }
}
