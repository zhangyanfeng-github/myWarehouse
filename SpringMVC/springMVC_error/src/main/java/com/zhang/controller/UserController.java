package com.zhang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    //空指针异常
    @GetMapping("null")
    public String data1() {
//        String name = null;
//        name.toString();
        System.out.println("UserController.data1");
        return "OK";
    }

    //除数异常
    @GetMapping("zero")
    public String data2() {
//        int i = 1 / 0;
        System.out.println("UserController.data2");
        return "ok";
    }
}
