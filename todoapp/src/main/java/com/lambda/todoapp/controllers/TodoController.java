package com.lambda.todoapp.controllers;

import com.lambda.todoapp.models.Todo;
import com.lambda.todoapp.models.User;
import com.lambda.todoapp.services.TodoService;
import com.lambda.todoapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/todos")
public class TodoController {

    /**
     * Using the Todo service to process user data
     */
    @Autowired
    TodoService todoService;

    @Autowired
    UserService userService;

    @PostMapping(value = "/user/{userid}", consumes = {"application/json"})
    public ResponseEntity<?> addNewTodo(@Valid @RequestBody Todo todo,
                                        @PathVariable long userid) {

        User currentUser = userService.findUserById(userid);
        Todo newTodo = new Todo(currentUser, todo.getDescription());

        todoService.save(newTodo);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }


}
