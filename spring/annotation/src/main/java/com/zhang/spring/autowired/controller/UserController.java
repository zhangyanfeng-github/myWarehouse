package com.zhang.spring.autowired.controller;

import com.zhang.spring.autowired.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    //注入Service
    //第一种方式，通过属性注入：根据类型找到对应对象，完成注入
//    @Autowired  //UserService是个接口，找到他对应的实现类对象，根据对象完成注入
//    private UserService userService;

    //第二种方式，通过set方法注入
//    private UserService userService;
//
//    @Autowired      //在set方法上加上@Autowired注解
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    //第三种方式，通过构造方法注入
//    private UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    //第四种方式，通过形参上的注入（同样生成构造方法）
//    private UserService userService;
//
//    public UserController(@Autowired UserService userService) {
//        this.userService = userService;
//    }

    //第五种方式，只有一个有参数构造函数，无注解
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void add(){
        System.out.println("controller......");
        userService.add();
    }
}
