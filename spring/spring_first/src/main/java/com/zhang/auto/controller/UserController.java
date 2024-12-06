package com.zhang.auto.controller;

import com.zhang.auto.service.UserService;

public class UserController {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void addUser(){
        System.out.println("controller中的方法执行了。。。");
        userService.addUserService();
    }
}
