package com.zhang.controller;

import com.zhang.entity.User;
import com.zhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author : 张彦锋
 * Date : 2024/12/14  14:34
 * Text :
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Object addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping
    public Object getUserById(Integer id){
        return userService.getUserById(id);
    }
}
