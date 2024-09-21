package com.example.spring_todolist.Dto;

import lombok.Data;

@Data
public class TodoDto {
    private Long userId;
    private Long todoId;
    private String title;
    private String context;
    private Integer status;
}
