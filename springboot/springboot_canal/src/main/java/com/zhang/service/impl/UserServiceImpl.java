package com.zhang.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.pojo.User;
import com.zhang.service.RedisService;
import com.zhang.service.UserService;
import com.zhang.mapper.UserMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 张彦锋
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2024-12-07 20:45:07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public Object addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Object updateUser(User user) {

        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();

        lambdaUpdateWrapper.eq(User::getUid, user.getUid())
                .set(User::getUserName, user.getUserName())
                .set(User::getUserAge, user.getUserAge())
                .set(User::getUserPhone, user.getUserPhone())
                .set(User::getUserEmail, user.getUserEmail());

        int row = userMapper.update(lambdaUpdateWrapper);
        if (row > 0) {
            return "修改成功";
        }
        return "用户不存在";
    }

    @Override
    public Object deleteUser(Integer id) {
        int row = userMapper.deleteById(id);
        if (row > 0) {
            return "删除成功";
        }
        return "没有此用户";
    }

    @Override
    public Object getUserBYId(Integer id) {

        User user = null;
        String key = "user:" + id;

        user = redisService.getUserByKey(key);

        if (user == null) {
            user = userMapper.selectById(id);
            if (user == null) {
                return "用户不存在";
            }
            redisService.saveUser(user);
            return user;
        }

        return user;
    }
}




