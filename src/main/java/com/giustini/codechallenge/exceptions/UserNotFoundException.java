package com.giustini.codechallenge.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Integer userId) {
        super(String.format("User with id \"%d\" cannot be found", userId));
    }
}
