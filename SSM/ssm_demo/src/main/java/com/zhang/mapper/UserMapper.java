package com.zhang.mapper;

import com.zhang.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> selectAll();

    int deleteUserById(Integer id);

    int insert(User user);

    int update(User user);
}
