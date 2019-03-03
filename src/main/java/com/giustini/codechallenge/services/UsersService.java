package com.giustini.codechallenge.services;

import com.giustini.codechallenge.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.singletonList;

@Service
public class UsersService {

    public List<User> getUsers() {
        return singletonList(new User());
    }

    public User getUserById(Integer userId) {
        return new User();
    }

}
