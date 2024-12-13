package com.zhang.service;

import com.zhang.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 张彦锋
* @description 针对表【t_user】的数据库操作Service
* @createDate 2024-12-07 20:45:07
*/
public interface UserService extends IService<User> {

    Object addUser(User user);

    Object updateUser(User user);

    Object deleteUser(Integer id);

    Object getUserBYId(Integer id);
}
