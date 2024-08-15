package com.example.spring_todolist.Dto;

import lombok.Data;

@Data
public class TodoDto {
    private String title;
    private String context;
    private Long userId;
    private Integer status;
}
