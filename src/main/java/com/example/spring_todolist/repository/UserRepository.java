package com.example.spring_todolist.repository;


import com.example.spring_todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
