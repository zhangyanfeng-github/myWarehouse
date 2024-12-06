package com.zhang.service;

import com.zhang.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 张彦锋
* @description 针对表【t_user】的数据库操作Service
* @createDate 2024-11-17 17:38:35
*/
public interface UserService extends IService<User> {

    Object addUser(User user);

    Object selectUserById(int id);

    Object updateUserById(User user);

    Object deleteUserById(int id);

    Object selectAllUser();
}
