package com.zhang.service;

import com.zhang.mapper.UserMapper;
import com.zhang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public int delete() {
        int row = userMapper.delete(2);
        return row;
    }

    public List<User> queryAll() {
        return userMapper.queryAll();
    }
}


