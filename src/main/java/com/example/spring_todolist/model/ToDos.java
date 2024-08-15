package com.example.spring_todolist.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;


@Entity
@Table(name = "Todos")
@Data //相當於是懶人包 包含 @Getter/@Setter @ToString @EqualsAndHashCode @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class ToDos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todo_id;

    //外鍵，多對一關係通常會加上JoinColumn
    @ManyToOne
    @JsonBackReference //避免傳送Json時無限循環 這邊是忽略
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String title;

    private String context;

    private Integer status;//完成狀態

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Date createTime = new Date();

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime = new Date();


}
