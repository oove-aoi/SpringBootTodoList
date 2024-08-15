package com.example.spring_todolist.repository;

import com.example.spring_todolist.model.ToDos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodosRepository extends JpaRepository<ToDos, Long> {
}
