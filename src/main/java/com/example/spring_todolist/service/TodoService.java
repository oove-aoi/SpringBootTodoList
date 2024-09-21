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
import java.util.stream.Collectors;

@Service
public class TodoService {
    @Autowired
    private TodosRepository todosRepository;
    @Autowired
    private UserRepository userRepository;



    public TodoDto getDtoById(Long id) {
        Optional<ToDos> todo = todosRepository.findById(id);
        ToDos todoEntity = todo.orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));

        return convertToDto(todoEntity);
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
        //單純用boolean好像不能分析到底哪裡有問題，可能要用其他方式新增訊息
        Optional<ToDos> isExistTodo = getOptionalToDosById(id);

        if(isExistTodo.isEmpty()) {
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
        Optional<ToDos> findTodo = getOptionalToDosById(id);
        if(findTodo.isEmpty()) {
            return false;
        }
        todosRepository.deleteById(id);
        return true;
    }

    //查詢User底下的所有Todos
    public List<ToDos> getTodosByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return todosRepository.findByUser(user);
    }

    //使用stream()流操作將todos全部轉成TodoDto(使用下面的convertToDto)，然後再蒐集起來弄成一個List
    public List<TodoDto> todosListToDtoList(List<ToDos> todos) {
        return todos.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //將ToDos轉換成Dto
    private TodoDto convertToDto(ToDos todo) {
        TodoDto dto = new TodoDto();

        dto.setUserId(todo.getUser().getUser_id()); // 設置 userId
        dto.setTodoId(todo.getTodo_id());
        dto.setTitle(todo.getTitle());
        dto.setContext(todo.getContext());
        dto.setStatus(todo.getStatus());

        return dto;
    }

    private Optional<ToDos> getOptionalToDosById(Long id){
        return todosRepository.findById(id);
    }
}
