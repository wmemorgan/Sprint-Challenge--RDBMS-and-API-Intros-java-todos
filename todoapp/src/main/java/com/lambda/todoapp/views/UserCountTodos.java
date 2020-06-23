package com.lambda.todoapp.views;

/**
 * View to display the results of the getUserCountTodos() custom query
 * @see UserRepository#getUserCountTodos() UserRepository.getUserCountTodos()
 */
public interface UserCountTodos {
    String getUsernamerpt();
    int getCounttodos();
}
