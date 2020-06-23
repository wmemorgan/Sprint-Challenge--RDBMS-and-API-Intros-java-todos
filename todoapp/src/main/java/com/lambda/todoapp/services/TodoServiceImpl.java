package com.lambda.todoapp.services;

import com.lambda.todoapp.models.Todo;
import com.lambda.todoapp.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> findAll() {

        List<Todo> todoList = new ArrayList<>();

        todoRepository.findAll()
                .iterator()
                .forEachRemaining(todoList::add);

        return todoList;
    }

    @Override
    public Todo findTodoById(long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo id " + id + " not found"));
    }

    @Override
    public Todo save(Todo todo) {

        Todo newTodo = new Todo();

        // Handle todo record replacement
        if (todo.getTodoid() != 0) {
            todoRepository.findById(todo.getTodoid())
                    .orElseThrow(() -> new EntityNotFoundException("Todo id " + todo.getTodoid() + " not found"));
            newTodo.setTodoid(todo.getTodoid());
        }

        newTodo.setUser(todo.getUser());
        newTodo.setDescription(todo.getDescription());

        return todoRepository.save(newTodo);
    }


    @Override
    public Todo update(Todo todo, long id) {
        Todo currentTodo = findTodoById(id);

        if (todo.getDescription() != null) {
            currentTodo.setDescription(todo.getDescription());
        }

        if (todo.isCompleted()) {
            currentTodo.setCompleted(todo.isCompleted());
        }

        return todoRepository.save(currentTodo);
    }

    @Override
    public void delete(long id) {
        todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo id " + id + " not found"));
        todoRepository.deleteById(id);
    }
}
