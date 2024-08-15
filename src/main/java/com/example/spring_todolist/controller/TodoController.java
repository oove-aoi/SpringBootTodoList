package com.example.spring_todolist.controller;

import com.example.spring_todolist.Dto.TodoDto;
import com.example.spring_todolist.model.ToDos;
import com.example.spring_todolist.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo API", description = "Operations for managing todos")
public class TodoController {
    @Autowired
    TodoService todoService;

    //缺找出特定用戶底下所有文章的功能

    @GetMapping
    @Operation(summary = "Get all todos", description = "Retrieves a list of all todos")
    public Iterable<ToDos> getTodoList() {
        Iterable<ToDos> todosList = todoService.getTodos();
        return todosList;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get todo by id", description = "Retrieves todo by id")
    public Optional<ToDos> getTodo(@PathVariable Long id) {
        Optional<ToDos> todo = todoService.findById(id);
        return todo;
    }

    @PostMapping
    @Operation(summary = "Create a new todo", description = "Create a new todo")
    public ResponseEntity<ToDos> createTodo(@RequestBody TodoDto todoDto) {
        ToDos createdTodo = todoService.createTodo(todoDto);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update todo data", description = "Update todo data")
    public ResponseEntity updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        Boolean rlt = todoService.updateTodo(id ,todoDto);
        if (!rlt) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status 欄位不能為空");
        }
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete todo by id", description = "Delete todo by id")
    public ResponseEntity deleteTodo(@PathVariable Long id) {
        Boolean rlt = todoService.deleteTodo(id);
        if (!rlt) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID不存在");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }


}
