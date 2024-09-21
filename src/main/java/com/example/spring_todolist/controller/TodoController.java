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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Tag(name = "Todo API", description = "Operations for managing todos")
public class TodoController {
    @Autowired
    TodoService todoService;

    //統一寫法，後頭要把東西塞進ResponseEntity再傳
    //記得所有的測試要跑一遍，然後SwaggerAPI的文件其他status code記得補上

    @GetMapping("/todos")
    @Operation(summary = "Get all todos", description = "Retrieves a list of all todos")
    public Iterable<TodoDto> getTodoList() {
        //使用DTO來顯示User_id，其餘的Json方法全部都不合意
        return todoService.todosListToDtoList(todoService.getTodos());
    }

    @GetMapping("/todos/{id}")
    @Operation(summary = "Get todo by id", description = "Retrieves todo by id")
    public TodoDto getTodo(@PathVariable Long id) {
        return todoService.getDtoById(id);
    }

    @PostMapping("/todos")
    @Operation(summary = "Create a new todo", description = "Create a new todo")
    public ResponseEntity<ToDos> createTodo(@RequestBody TodoDto todoDto) {
        ToDos createdTodo = todoService.createTodo(todoDto);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @PutMapping("/todos/{id}")
    @Operation(summary = "Update todo data", description = "Update todo data")
    public ResponseEntity<String> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        Boolean rlt = todoService.updateTodo(id ,todoDto);
        if (!rlt) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status 欄位不能為空");
        }
        return ResponseEntity.status(HttpStatus.OK).body("更新成功");
    }
    @DeleteMapping("/todos/{id}")
    @Operation(summary = "Delete todo by id", description = "Delete todo by id")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        Boolean rlt = todoService.deleteTodo(id);
        if (!rlt) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID不存在");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }


}
