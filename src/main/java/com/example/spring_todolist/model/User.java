package com.example.spring_todolist.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //基本用戶設定
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String email;
    private String username;
    private String password; //先用string後面再用Spring Security PasswordEncoder去處理

    //外鍵 一對多關係 通常會使用List或set
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference//避免傳送Json時無限循環 這邊是設為主要來源
    private List<ToDos> todos;

}
