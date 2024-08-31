package com.example.spring_todolist.repository;

import com.example.spring_todolist.model.ToDos;
import com.example.spring_todolist.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodosRepository extends JpaRepository<ToDos, Long> {

    //查詢某個user底下所有的todo
    List<ToDos> findByUser(User user);

}
