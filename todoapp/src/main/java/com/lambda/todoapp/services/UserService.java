package com.lambda.todoapp.services;

import com.lambda.todoapp.models.User;

import java.util.List;

/**
 * The service that works with the User model.
 */
public interface UserService {
    
    /**
     * Returns a list of all User objects
     *
     * @return list of all User object
     */
    List<User> findAll();

    /**
     * Return the first User matching the given primary key
     *
     * @param id The primary key (long) of the User you seek
     * @return The User object you seek
     */
    User findUserById(long id);

    /**
     * Given a complete user object, saves that user object in the database.
     * If a primary key is provided, the record is completely replaced
     * If no primary key is provided, one is automatically generated and the record is added to the database.
     *
     * @param user the user object to be saved
     * @return the saved user object including any automatically generated fields
     */
    User save(User user);

    /**
     * Deletes the user record and its telephone and animal items from the database based off of the provided primary key
     *
     * @param id id The primary key (long) of the user you seek.
     */
    void delete(long id);
}
