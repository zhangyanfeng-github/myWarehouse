package com.zhang.mapper;

import com.zhang.pojo.User;

import java.util.List;

public interface UserMapper {

    int insertUser(String name, int age, String sex);

    int deleteUserById(int id);

    int updateUserNameById(String name, int id);

    List<User> selectAllUser();

    int updateUserAgeById(int age, int id);

    User selectUserById(int id);
}
