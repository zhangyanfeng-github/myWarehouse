package com.zhang.controller;

import com.zhang.pojo.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@RequestMapping("user")
public class UserController {
    //分页查询功能
    @GetMapping
    public List<User> page(@RequestParam(required = false, defaultValue = "1") int page,
                           @RequestParam(required = false, defaultValue = "10") int size) {
        System.out.println("page = " + page + ", size = " + size);
        return null;
    }

    //用户添加功能，前端传过来的是json数据
    @PostMapping
    public User save(@RequestBody User user) {
        return user;
    }

    //根据ID删除功能，前端传来ID值，根据路径传参，需要@PathVariable注解
    @DeleteMapping("{id}")
    public User delete(@PathVariable Integer id) {
        return null;
    }

    //修改功能
    @PutMapping
    public User update(@RequestBody User user) {
        return user;
    }

    //查询功能
    @GetMapping("{id}")
    public User select(@PathVariable Integer id) {
        return null;
    }

    //条件模糊查询
    @GetMapping("search")
    public List<User> search(String keyword,
                             @RequestParam(required = false, defaultValue = "1") int page,
                             @RequestParam(required = false, defaultValue = "10") int size) {
        return null;
    }

}
