package com.lambda.todoapp.repositories;

import com.lambda.todoapp.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * The CRUD repository connecting User model to the rest of the application
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
