package com.giustini.codechallenge.services.rest;

import com.giustini.codechallenge.exceptions.UserNotFoundException;
import com.giustini.codechallenge.models.User;
import com.giustini.codechallenge.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    UsersService usersService;


    @GetMapping("getusers")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(usersService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("createUsers")
    public ResponseEntity<User> createUser(
            @RequestBody(required = false) User user
    ) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<User>(usersService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("getusersById/{userId}")
    public ResponseEntity<User> getUserById(
            @PathVariable Integer userId
    ) {
        try {
            return new ResponseEntity<>(usersService.getUserById(userId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("updateUsersById/{userId}")
    public ResponseEntity<User> updateUserById(
            @PathVariable Integer userId,
            @RequestBody(required = true) User user
    ) {
        try {
            return new ResponseEntity<User>(usersService.updateUserById(userId, user), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("deleteUsersById/{userId}")
    public ResponseEntity deleteUserById(
            @PathVariable Integer userId
    ) {
        try {
            usersService.deleteUserById(userId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
