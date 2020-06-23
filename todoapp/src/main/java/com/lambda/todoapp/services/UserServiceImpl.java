package com.lambda.todoapp.services;

import com.lambda.todoapp.models.Todo;
import com.lambda.todoapp.models.User;
import com.lambda.todoapp.repositories.TodoRepository;
import com.lambda.todoapp.repositories.UserRepository;
import com.lambda.todoapp.views.UserNameCountTodos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TodoRepository todoRepository;

    @Override
    public List<User> findAll() {

        List<User> userList = new ArrayList<>();

        userRepository.findAll()
                .iterator()
                .forEachRemaining(userList::add);

        return userList;
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User " + id + " not found"));
    }

    @Override
    public List<UserNameCountTodos> getUserNameCountTodos() {
        return userRepository.getUserNameCountTodos();
    }

    @Override
    public User save(User user) {

        User newUser = new User();

        // Handle user record replacement
        if (user.getUserid() != 0) {
            userRepository.findById(user.getUserid())
                    .orElseThrow(() -> new EntityNotFoundException("User " + user.getUserid() + " not found"));

            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername());
        newUser.setPrimaryemail(user.getPrimaryemail());
        newUser.setPassword(user.getPassword());

        newUser.getTodos().clear();

        for (Todo t: user.getTodos()) {
            Todo newTodo = new Todo(newUser, t.getDescription());
            newUser.getTodos().add(newTodo);
        }

        return userRepository.save(newUser);
    }

    @Override
    public void delete(long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User " + id + " not found"));
        userRepository.deleteById(id);
    }
}
