package com.zhang.controller;

import com.zhang.pojo.User;
import com.zhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("select")
    public List<User> queryAll() {
        return userService.queryAll();
    }

    @GetMapping("delete")
    public int delete(){
        return userService.delete();
    }
}
