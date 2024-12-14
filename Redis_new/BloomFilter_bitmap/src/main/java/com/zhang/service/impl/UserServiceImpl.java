package com.zhang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhang.entity.User;
import com.zhang.service.UserService;
import com.zhang.mapper.UserMapper;
import com.zhang.utils.CheckUtils;
import io.netty.util.internal.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author 张彦锋
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2024-12-14 14:33:28
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final String USER = "user:";

    @Autowired
    private CheckUtils checkUtils;

    @Override
    public Object addUser(User user) {

        int insert = userMapper.insert(user);
        if (insert > 0) {
            User select = userMapper.selectById(user.getUid());
            String key = USER + select.getUid();
            redisTemplate.opsForValue().set(key, select);

            return "添加成功";
        }

        return "添加失败";
    }

    @Override
    public Object getUserById(Integer id) {
        String key = USER + id;
        User user = (User) redisTemplate.opsForValue().get(key);

        //加入BloomFilter，检查这个key在布隆过滤器中，如果有则到Redis，没有直接返回。
        if (!checkUtils.checkBloomFilter("whitelistUser", key)) {
            log.info("布隆过滤器中不存在该key" + key);
            return "用户不存在";
        }

        //其余结构不变

        if (ObjectUtils.isEmpty(user)) {
            user = userMapper.selectById(id);

            if (ObjectUtils.isEmpty(user)) {
                return "用户不存在";
            }

            redisTemplate.opsForValue().set(key, user);
            return user;

        }
        return user;
    }
}