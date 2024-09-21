package com.example.spring_todolist.controller;

import com.example.spring_todolist.Dto.UserDto;
import com.example.spring_todolist.model.User;
import com.example.spring_todolist.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "Operations for managing users")
public class UserController {
    @Autowired
    private UserService userService;

    /*
    Optional<> 比較常用在服務層或資料存取層，好處理沒找到資料的狀況
    ResponseEntity<> 用在Controller中封裝回應

     */
    @GetMapping("/{id}")
    @Operation(summary = "Get user and All todo by User-ID", description = "Retrieves user based on ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success found the user"),
            @ApiResponse(responseCode = "404", description = "Can't found user"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);

        //JPARepository返回值為Optional<User>，有沒有找到值要用isPresent()來判斷
        if(user.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User user = userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update User", description = "Updata User")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                             @RequestBody UserDto userDto) {

        //檢查使用者存不存在
        Optional<User> user = userService.getUserById(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //修改其中的數據
        userService.updateUser(id, userDto);

        Optional<User> updateUser = userService.getUserById(id);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    // 加上User刪除的方法，並統一寫法
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete User", description = "Delete User")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

    /*
    測試練習 但找所有Users真的用不到阿
    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieves a list of all users")
    public Iterable<User> getUserList() {
        return userService.getUsers();
    }
    */

}
