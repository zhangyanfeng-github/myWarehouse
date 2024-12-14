package com.zhang.service;

import com.zhang.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 张彦锋
* @description 针对表【t_user】的数据库操作Service
* @createDate 2024-12-14 14:33:28
*/
public interface UserService extends IService<User> {

    Object addUser(User user);

    Object getUserById(Integer id);
}
