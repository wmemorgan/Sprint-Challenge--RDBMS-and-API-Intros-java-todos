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
     * Using the Todo service to process todo data
     */
    @Autowired
    TodoService todoService;

    /**
     * Using the User service to process todo data
     */
    @Autowired
    UserService userService;

    /**
     * Given a complete User Object, create a new User record and accompanying telephone records
     * and animal records.
     * <br> Example: <a href="http://localhost:2019/todos/todo">http://localhost:2019/todos/todo</a>
     *
     * @param todo A Todo object to containing task description.
     * @param userid User id associated with the task  
     * @return HttpStatus
     * @see UserService#save(User) UserService.save(User)
     */
    @PostMapping(value = "/todo/{userid}", consumes = {"application/json"})
    public ResponseEntity<?> addNewTodo(@Valid @RequestBody Todo todo,
                                        @PathVariable long userid) {

        User currentUser = userService.findUserById(userid);
        Todo newTodo = new Todo(currentUser, todo.getDescription());

        todoService.save(newTodo);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    /**
     * Updates the todo record associated with the given id with the provided data. Only the provided fields are affected.
     * <br> Example: <a href="http://localhost:2019/todos/todo/7">http://localhost:2019/todos/todo/7</a>
     *
     * @param todoid The primary key of the todo you wish to update.
     * @return A status of OK
     * @see TodoService#update(Todo, long)  TodoService.update(Todo, long)
     */
    @PatchMapping(value = "/todo/{todoid}")
    public ResponseEntity<?> completeTodo(@PathVariable long todoid) {

        // Prevent marking complete a previously completed task
        if (todoService.findTodoById(todoid).isCompleted()) {
            String msg = "Task is already complete";

            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

        Todo updateTodo = todoService.findTodoById(todoid);
        updateTodo.setCompleted(true);
        todoService.update(updateTodo, todoid);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
