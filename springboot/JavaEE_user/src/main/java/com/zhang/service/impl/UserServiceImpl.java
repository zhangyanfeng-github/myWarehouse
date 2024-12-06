package com.zhang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.pojo.User;
import com.zhang.service.UserService;
import com.zhang.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 张彦锋
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2024-11-17 17:38:35
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Object addUser(User user) {
        int rows = userMapper.insert(user);
        if (rows > 0) {
            return "添加成功";
        }
        return "添加失败";
    }

    @Override
    public Object deleteUserById(int id) {

        int rows = userMapper.deleteById(id);

        if (rows > 0) {
            return "删除成功";
        }

        return "用户不存在";
    }

    @Override
    public Object selectAllUser() {
        return userMapper.selectList(null);
    }

    @Override
    public Object updateUserById(User user) {

        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();

        lambdaUpdateWrapper.eq(User::getUid, user.getUid())
                .set(User::getUserName, user.getUserName())
                .set(User::getUserAge, user.getUserAge())
                .set(User::getUserPhone, user.getUserPhone())
                .set(User::getUserEmail, user.getUserEmail());

        int rows = userMapper.update(lambdaUpdateWrapper);

        if (rows > 0) {
            return "修改成功";
        }

        return "没有此用户";
    }

    @Override
    public Object selectUserById(int id) {

        User user = userMapper.selectById(id);

        if (user == null) {
            return "没有此用户";
        }

        return user;
    }
}




