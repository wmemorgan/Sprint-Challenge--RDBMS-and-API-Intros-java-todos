package com.lambda.todoapp.controllers;

import com.lambda.todoapp.models.User;
import com.lambda.todoapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping(value = "/user", consumes = {"application/json"})
    public ResponseEntity<?> addUser(@Valid @RequestBody User newUser) {
        newUser.setUserid(0);
        newUser = userService.save(newUser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newUser.getUserid())
                .toUri();

        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/user/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable long userid) {
        userService.delete(userid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
