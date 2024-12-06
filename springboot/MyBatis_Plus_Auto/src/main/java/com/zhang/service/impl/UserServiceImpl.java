package com.zhang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.pojo.User;
import com.zhang.service.UserService;
import com.zhang.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 张彦锋
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-11-15 20:40:19
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




