package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;

public interface TodosService
{
    void markComplete(long todoid);

    void update(Todos todos, long todoid);
}
