package com.lambda.todoapp.repositories;

import com.lambda.todoapp.models.User;
import com.lambda.todoapp.views.UserNameCountTodos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The CRUD repository connecting User model to the rest of the application
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * List all users and a count of how many open todos under their user record
     * @return List of usernames and the number of open todos
     */
    @Query(value = "SELECT u.username as usernamerpt, COUNT(u.username) AS counttodos FROM todos JOIN users u on todos.userid = u.userid WHERE completed = false GROUP BY u.username ORDER BY u.username",
            nativeQuery = true)
    List<UserNameCountTodos> getUserNameCountTodos();

}
