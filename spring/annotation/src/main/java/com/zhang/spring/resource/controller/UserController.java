package com.zhang.spring.resource.controller;

import com.zhang.spring.resource.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;

@Controller(value = "myUserController")
public class UserController {

    //第一种：根据名称进行注入
//    @Resource(name = "myUserService")
//    private UserService userService;

    //第三种根据类型注入（既没有指定名字，也没有指定属性名称）
    @Resource
    private UserService userService;

    public void add(){
        System.out.println("controller......");
        userService.add();
    }
}
