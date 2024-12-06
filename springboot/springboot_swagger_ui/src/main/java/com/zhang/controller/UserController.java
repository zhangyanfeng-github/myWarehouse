package com.zhang.controller;

import com.zhang.pojo.User;
import com.zhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Author : 张彦锋
 * Date : 2024/11/22  19:52
 * Text : 控制层
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Gets all user.
     *
     * @return the all user
     */
    @GetMapping
    public Object getAllUser() {
        return userService.getAllUser();
    }

    /**
     * Add user object.
     *
     * @param user the user
     * @return the object
     */
    @PostMapping
    public Object addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * Update user by id object.
     *
     * @param user the user
     * @return the object
     */
    @PutMapping
    public Object updateUserById(@RequestBody User user) {
        return userService.updateUserById(user);
    }

    /**
     * Gets user by id.
     *
     * @param uid the uid
     * @return the user by id
     */
    @GetMapping("{uid}")
    public Object getUserById(@PathVariable Integer uid) {

        return userService.getUserById(uid);
    }

    /**
     * Delete user by id object.
     *
     * @param uid the uid
     * @return the object
     */
    @DeleteMapping
    public Object deleteUserById(@RequestParam Integer uid) {
        return userService.deleteUserById(uid);
    }

    /**
     * Get user page object.
     *
     * @param pageNum  the page num
     * @param pageSize the page size
     * @return the object
     */
    @GetMapping("{pageNum}/{pageSize}")
    public Object getUserPage(@PathVariable Integer pageNum,@PathVariable Integer pageSize){
        return userService.getUserPage(pageNum,pageSize);
    }


}
