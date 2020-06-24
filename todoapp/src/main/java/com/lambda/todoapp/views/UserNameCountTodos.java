package com.lambda.todoapp.views;

/**
 * View to display the results of the getUserCountTodos() custom query
 * @see UserRepository#getUserNameCountTodos() UserRepository.getUserNameCountTodos()
 */
public interface UserNameCountTodos {
    String getUsernamerpt();
    int getCounttodos();
}
