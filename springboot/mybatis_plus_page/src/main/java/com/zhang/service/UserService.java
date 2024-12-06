package com.zhang.service;

import com.zhang.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 张彦锋
* @description 针对表【t_user】的数据库操作Service
* @createDate 2024-11-19 15:33:39
*/
public interface UserService extends IService<User> {
    Object getUserPage(Integer current, Integer size);
}
