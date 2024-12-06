package com.zhang.controller;

import com.zhang.pojo.User;
import com.zhang.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Object addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("{id}")
    public Object selectUserById(@PathVariable int id) {
        return userService.selectUserById(id);
    }

    @GetMapping
    public Object selectAllUser() {
        return userService.selectAllUser();
    }

    @PutMapping
    public Object updateUserById(@RequestBody User user) {
        return userService.updateUserById(user);
    }

    @DeleteMapping
    public Object deleteUserById(@RequestParam int id) {
        return userService.deleteUserById(id);
    }

}
