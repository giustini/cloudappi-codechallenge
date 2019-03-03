package com.giustini.codechallenge.services.rest;

import com.giustini.codechallenge.models.User;
import com.giustini.codechallenge.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping("getusers")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(usersService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("getusersById/{userId}")
    public ResponseEntity<User> getUserById(
            @PathVariable Integer userId
    ) {
        return new ResponseEntity<>(usersService.getUserById(userId), HttpStatus.OK);
    }
}
