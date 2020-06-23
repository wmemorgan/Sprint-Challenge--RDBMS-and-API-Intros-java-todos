package com.lambda.todoapp.repositories;

import com.lambda.todoapp.models.User;
import com.lambda.todoapp.views.UserCountTodos;
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
    @Query(value = "SELECT U.USERNAME as usernamerpt, COUNT(U.USERNAME) AS counttodos FROM TODOS JOIN USERS U on TODOS.USERID = U.USERID WHERE COMPLETED = false GROUP BY U.USERNAME ORDER BY U.USERNAME",
            nativeQuery = true)
    List<UserCountTodos> getUserCountTodos();

}
