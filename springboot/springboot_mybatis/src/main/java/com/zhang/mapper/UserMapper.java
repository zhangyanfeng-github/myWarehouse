package com.zhang.mapper;

import com.zhang.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> queryAll();

    int delete(int id);
}
