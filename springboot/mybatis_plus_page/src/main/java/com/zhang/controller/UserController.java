package com.zhang.controller;

import com.zhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{current}/{size}")
    public Object getUserPage(@PathVariable Integer current, @PathVariable Integer size) {
        return userService.getUserPage(current, size);
    }
}
