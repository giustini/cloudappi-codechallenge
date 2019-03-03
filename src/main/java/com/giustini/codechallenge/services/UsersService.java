package com.giustini.codechallenge.services;

import com.giustini.codechallenge.exceptions.UserNotFoundException;
import com.giustini.codechallenge.models.User;
import com.giustini.codechallenge.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;


    public List<User> getUsers() {
        return usersRepository.findAll();
    }

    public User createUser(User user) {
        return usersRepository.save(user);
    }

    public User getUserById(Integer userId) throws UserNotFoundException {
        return usersRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User updateUserById(Integer userId, User user) throws UserNotFoundException {
        if (!usersRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        user.setId(userId);
        return usersRepository.save(user);
    }

    public void deleteUserById(Integer userId) throws UserNotFoundException {
        if (!usersRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        usersRepository.deleteById(userId);
    }
}
