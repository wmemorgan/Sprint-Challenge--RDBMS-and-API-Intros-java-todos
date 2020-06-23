package com.lambda.todoapp.repositories;


import com.lambda.todoapp.models.Todo;
import org.springframework.data.repository.CrudRepository;

/**
 * The CRUD repository connecting Todo model to the rest of the application
 */
public interface TodoRepository extends CrudRepository<Todo, Long> {
    
}
