package com.lambda.todoapp.controllers;

import com.lambda.todoapp.models.User;
import com.lambda.todoapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Using the User service to process user data
     */
    @Autowired
    UserService userService;

    /**
     * List of all users and their todos
     * <br>Example: <a href="http://localhost:2019/users/users">http://localhost:2019/users/users</a>
     *
     * @return JSON List of all users and their associated todos
     * @see UserService#findAll() UserService.findAll()
     */
    @GetMapping(value = "/users", produces = {"application/json"})
    public ResponseEntity<?> listAllUsers() {
        List<User> userList = userService.findAll();
        
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    /**
     * Returns a single user based off a user id number
     * <br>Example: http://localhost:2019/zoos/user/3
     *
     * @param userid The primary key of the user you seek
     * @return JSON object of the user you seek
     * @see UserService#findUserById(long)  UserService.findUserById(long)
     */
    @GetMapping(value = "/user/{userid}", produces = {"application/json"})
    public ResponseEntity<?> findUserById(@PathVariable long userid) {
        
        User u = userService.findUserById(userid);
        
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
}
