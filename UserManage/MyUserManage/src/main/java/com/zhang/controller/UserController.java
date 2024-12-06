package com.zhang.controller;

import com.zhang.pojo.User;
import com.zhang.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController     //不经过视图解析器
@RequestMapping("user")
@Api(tags = "用户信息")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("添加用户")
    @RequestMapping(value = "insertUser", method = RequestMethod.GET)
    public int insertUser(@RequestParam String name,
                          @RequestParam int age,
                          @RequestParam String sex) {
        System.out.println("添加成功！");
        return userService.insertUser(name, age, sex);
    }

    @ApiOperation("通过ID删除用户")
    @RequestMapping(value = "deleteUserById", method = RequestMethod.GET)
    public int deleteUserById(@RequestParam int id) {
        System.out.println("删除成功！");
        return userService.deleteUserById(id);
    }

    @ApiOperation("通过ID修改用户姓名")
    @RequestMapping(value = "updateUserNameById", method = RequestMethod.GET)
    public int updateUserNameById(@RequestParam String name, @RequestParam int id) {
        System.out.println("姓名修改成功！");
        return userService.updateUserNameById(name, id);
    }

    @ApiOperation("通过ID修改用户年龄")
    @RequestMapping(value = "updateUserAgeById", method = RequestMethod.GET)
    public int updateUserAgeById(@RequestParam int age, @RequestParam int id) {
        System.out.println("年龄修改成功！");
        return userService.updateUserAgeById(age, id);
    }

    @ApiOperation("查询所有用户")
    @RequestMapping(value = "selectAllUser", method = RequestMethod.GET)
    public String selectAllUser() {
        List<User> users = userService.selectAllUser();
        for (User user : users) {
            System.out.println(user);
        }
        return "ok";
    }

    @ApiOperation("根据ID查询用户")
    @RequestMapping(value = "selectUserById", method = RequestMethod.GET)
    public String selectUserById(@RequestParam int id) {
        User user = userService.selectUserById(id);
        System.out.println(user);
        return "ok";
    }
}
