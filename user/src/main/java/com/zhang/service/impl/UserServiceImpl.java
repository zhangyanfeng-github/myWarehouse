package com.zhang.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.pojo.User;
import com.zhang.service.UserService;
import com.zhang.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 *
 * @author 张彦锋
 * @description 针对表 【t_user】的数据库操作Service实现
 * @createDate 2024 -11-22 19:51:51
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Object getAllUser() {
        return userMapper.selectList(null);
    }

    @Override
    public Object addUser(User user) {

        return userMapper.insert(user);
    }

    @Override
    public Object updateUserById(User user) {

        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();

        lambdaUpdateWrapper.eq(User::getUid, user.getUid())
                .set(User::getUserName, user.getUserName())
                .set(User::getUserAge, user.getUserAge())
                .set(User::getUserPhone, user.getUserPhone())
                .set(User::getUserEmail, user.getUserEmail());

        return userMapper.update(lambdaUpdateWrapper);
    }

    @Override
    public Object getUserById(Integer uid) {

        return userMapper.selectById(uid);
    }

    @Override
    public Object deleteUserById(Integer uid) {

        return userMapper.deleteById(uid);
    }

    @Override
    public Object getUserPage(Integer pageNum, Integer pageSize) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        return userMapper.selectPage(page, null);
    }
}




