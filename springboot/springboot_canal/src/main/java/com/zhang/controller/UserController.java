package com.zhang.controller;

import com.zhang.pojo.User;
import com.zhang.service.RedisService;
import com.zhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author : 张彦锋
 * Date : 2024/12/7  20:43
 * Text :
 */
@RestController
@RequestMapping("/canal")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Object getAllUser(@RequestParam Integer id) {
        return userService.getUserBYId(id);
    }

    @PostMapping
    public Object addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping
    public Object updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping
    public Object deleteUser(@RequestParam Integer id) {
        return userService.deleteUser(id);
    }
}
