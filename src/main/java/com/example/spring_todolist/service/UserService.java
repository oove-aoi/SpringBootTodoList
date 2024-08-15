package com.example.spring_todolist.service;

import com.example.spring_todolist.Dto.UserDto;
import com.example.spring_todolist.model.ToDos;
import com.example.spring_todolist.model.User;
import com.example.spring_todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    //Optional可以很好的處理空值，防止NullPointerException，提高代碼的可讀與可維護性
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public User createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return userRepository.save(user);
    }

    public void updateUser(Long id, UserDto userDto) {
        User newUser = new User();

        newUser.setEmail(userDto.getEmail());
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());

        userRepository.save(newUser);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
