package com.lambda.todoapp.controllers;

import com.lambda.todoapp.models.User;
import com.lambda.todoapp.repositories.UserRepository;
import com.lambda.todoapp.services.UserService;
import com.lambda.todoapp.views.UserCountTodos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Using the User service to process user data
     */
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

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
     * <br>Example: http://localhost:2019/users/user/3
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


    /**
     * List of all users and a count of open tasks associated with their user record
     * <br>Example: <a href="http://localhost:2019/users/users/todos">http://localhost:2019/users/users/todos</a>
     *
     * @return JSON List of all the animals and their associated users
     * @see UserRepository#getUserCountTodos() UserRepository.getUserCountTodos()
     */
    @GetMapping(value = "/users/todos", produces = {"applicaiton/json"})
    public ResponseEntity<?> listUsersOpenTodos() {
        List<UserCountTodos> userList = new ArrayList<>();

            userRepository.getUserCountTodos()
                    .iterator()
                    .forEachRemaining(userList::add);

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    /**
     * Given a complete User Object, create a new User record and accompanying telephone records
     * and animal records.
     * <br> Example: <a href="http://localhost:2019/users/user">http://localhost:2019/users/user</a>
     *
     * @param newUser A complete new user to add including telephone numbers and animals.
     *                animals must already exist.
     * @return A location header with the URI to the newly created user and a status of CREATED
     * @throws URISyntaxException Exception if something does not work in creating the location header
     * @see UserService#save(User) UserService.save(User)
     */
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

    /**
     * Deletes a given user along with associated telephone and animal records
     * <br>Example: <a href="http://localhost:2019/users/user/14">http://localhost:2019/users/user/14</a>
     *
     * @param userid the primary key of the user you wish to delete
     * @return Status of OK
     */
    @DeleteMapping(value="/user/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable long userid) {
        userService.delete(userid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
