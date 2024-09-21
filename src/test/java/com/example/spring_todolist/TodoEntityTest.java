package com.example.spring_todolist;

import com.example.spring_todolist.model.ToDos;
import com.example.spring_todolist.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenGetId_ThenSetId() {
        ToDos todo = new ToDos();
        todo.setTodo_id(Long.valueOf(1));

        Integer expected = 1;
        Integer actual = Math.toIntExact(todo.getTodo_id());

        assertEquals(expected, actual);
    }

    @Test
    public void whenGetContext_ThenSetContext() {
        ToDos todo = new ToDos();
        todo.setContext("洗衣服");

        String expected = "洗衣服";
        String actual = todo.getContext();

        assertEquals(expected, actual);
    }

    @Test
    public void whenGetTitle_ThenSetTitle() {
        ToDos todo = new ToDos();
        todo.setTitle("記得衣服要洗");

        String expected = "記得衣服要洗";
        String actual = todo.getTitle();

        assertEquals(expected, actual);
    }

    @Test
    public void whenFindUserById_thenSetTodoUser() {
        /*
        User user = new User();
        ToDos todo = new ToDos();


        user.setUser_id(Long.valueOf(1));
        todo.setTitle("中午去外面吃");
        todo.setUser(user);

        user.setTodos(Arrays.asList(todo));

        entityManager.persist(user);
        entityManager.flush();

        // When
        Optional<User> foundUserOpt = user.findById(user.getId());
        assertThat(foundUserOpt).isPresent();
        User foundUser = foundUserOpt.get();

        // Then
        assertThat(foundUser.getTodos()).hasSameClassAs(1).extracting(ToDos::getTitle).containsExactly("中午去外面吃");
        */
    }

}
