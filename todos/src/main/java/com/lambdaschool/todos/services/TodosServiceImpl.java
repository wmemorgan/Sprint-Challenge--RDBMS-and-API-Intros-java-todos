package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "todosService")
public class TodosServiceImpl implements TodosService {

    @Autowired
    TodosRepository todosRepository;

    @Override
    public void update(Todos todos, long todoid) {
        Todos t = todosRepository.findTodosByTodoid(todoid);

        if (t == null) {
            throw new EntityNotFoundException(
                    "Todo id " + todoid + " not found");
        }

        if (todos.getDescription() != null) {
            t.setDescription(todos.getDescription());
        }

        if (todos.isCompleted()) {
            t.setCompleted(todos.isCompleted());
        }

        todosRepository.save(t);
    }

    @Override
    public void markComplete(long todoid) {
        Todos t = todosRepository.findTodosByTodoid(todoid);

        if (t == null) {
            throw new EntityNotFoundException(
                    "Todo id " + todoid + " not found");
        }
        System.out.println("Todo id " + todoid +
                " " +
                t.getDescription() + " " +
                t.isCompleted());
        t.setCompleted(true);

        update(t, todoid);
    }
}
