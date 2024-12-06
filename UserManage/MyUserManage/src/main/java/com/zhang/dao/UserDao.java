package com.zhang.dao;

import com.zhang.mapper.UserMapper;
import com.zhang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    final private String USER = "user:";

    @Transactional
    public int insertUser(String name, int age, String sex) {
        return userMapper.insertUser(name, age, sex);
    }

    @Transactional
    public int deleteUserById(int id) {
        return userMapper.deleteUserById(id);
    }

    @Transactional
    public int updateUserNameById(String name, int id) {
        String key = USER + id;

        int i = userMapper.updateUserNameById(name, id);
        User user = userMapper.selectUserById(id);
        redisTemplate.opsForValue().set(key, user);
        return i;
    }

    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Transactional
    public int updateUserAgeById(int age, int id) {
        String key = USER + id;

        int i = userMapper.updateUserAgeById(age, id);
        User user = userMapper.selectUserById(id);
        redisTemplate.opsForValue().set(key, user);
        return i;
    }

    public User selectUserById(int id) {
        User user = null;
        String key = USER + id;

        user = (User) redisTemplate.opsForValue().get(key);
        if (user == null) {
            user = userMapper.selectUserById(id);

            if (user == null) {
                return user;
            } else {
                redisTemplate.opsForValue().set(key, user);
            }
        }
        return user;
    }
}
