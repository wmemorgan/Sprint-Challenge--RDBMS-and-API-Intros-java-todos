package com.lambda.todoapp.services;

import com.lambda.todoapp.models.Todo;
import com.lambda.todoapp.models.User;

import java.util.List;

/**
 * The service that works with the Todo model.
 */
public interface TodoService {

    /**
     * Returns a list of all Todo objects
     *
     * @return list of all Todo object
     */
    List<Todo> findAll();

    /**
     * Return the first Todo matching the given primary key
     *
     * @param id The primary key (long) of the Todo you seek
     * @return The Todo object you seek
     */
    Todo findTodoById(long id);
    

    /**
     * Given a complete todo object, saves that todo object in the database.
     * If a primary key is provided, the record is completely replaced
     * If no primary key is provided, one is automatically generated and the record is added to the database.
     *
     * @param todo the user associated with the task
     */
    void save(Todo todo);

    /**
     * Updates the provided fields in the todo record referenced by the primary key.
     *
     * @param todo just the todo fields to be updated.
     * @param id   The primary key (long) of the todo to update
     */
    void update(Todo todo, long id);

    /**
     * Deletes the todo record and its telephone and animal items from the database based off of the provided primary key
     *
     * @param id id The primary key (long) of the todo you seek.
     */
    void delete(long id);
    
}
