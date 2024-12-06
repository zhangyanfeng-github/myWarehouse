package com.zhang.service;

import com.zhang.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 张彦锋
* @description 针对表【t_user】的数据库操作Service
* @createDate 2024-11-22 19:51:51
*/
public interface UserService extends IService<User> {

    Object getAllUser();

    Object addUser(User user);

    Object updateUserById(User user);

    Object getUserById(Integer uid);

    Object deleteUserById(Integer uid);

    Object getUserPage(Integer pageNum, Integer pageSize);
}
