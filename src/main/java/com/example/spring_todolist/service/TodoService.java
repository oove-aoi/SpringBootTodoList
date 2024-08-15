package com.example.spring_todolist.service;

import com.example.spring_todolist.Dto.TodoDto;
import com.example.spring_todolist.model.ToDos;
import com.example.spring_todolist.model.User;
import com.example.spring_todolist.repository.TodosRepository;
import com.example.spring_todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class TodoService {
    @Autowired
    private TodosRepository todosRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<ToDos> findById(Long id) {
        Optional<ToDos> todo = todosRepository.findById(id);
        return todo;
    }

    public List<ToDos> getTodos() {
        return todosRepository.findAll();
    }

    public ToDos createTodo(TodoDto todoDto) {
        ToDos todo = new ToDos();

        User user = userRepository.findById(todoDto.getUserId()).orElse(null);
        todo.setUser(user);

        todo.setTitle(todoDto.getTitle());
        todo.setContext(todoDto.getContext());
        todo.setStatus(todoDto.getStatus());

        return todosRepository.save(todo);
    }

    public Boolean updateTodo(Long id, TodoDto todoDto) {
        Optional<ToDos> isExistTodo = findById(id);

        if(! isExistTodo.isPresent()) {
            return false;
        }

        ToDos newTodo = isExistTodo.get();
        Long newUserId = newTodo.getUser().getUser_id();
        Long oldUserId = todoDto.getUserId();

        if(todoDto.getStatus() == null || !newUserId.equals(oldUserId)) {
            return false;
        }

        newTodo.setTitle(todoDto.getTitle());
        newTodo.setContext(todoDto.getContext());
        newTodo.setStatus(todoDto.getStatus());

        todosRepository.save(newTodo);
        return  true;
    }

    public Boolean deleteTodo(Long id) {
        Optional<ToDos> findTodo = findById(id);
        if(!findTodo.isPresent()) {
            return false;
        }
        todosRepository.deleteById(id);
        return true;
    }
}
