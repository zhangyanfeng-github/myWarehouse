package com.zhang.service;

import com.zhang.dao.UserDao;
import com.zhang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public int insertUser(String name, int age, String sex) {
        return userDao.insertUser(name, age, sex);
    }

    public int deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }

    public int updateUserNameById(String name, int id) {
        return userDao.updateUserNameById(name, id);
    }

    public List<User> selectAllUser() {
        return userDao.selectAllUser();
    }

    public int updateUserAgeById(int age, int id) {
        return userDao.updateUserAgeById(age, id);
    }

    public User selectUserById(int id) {
        return userDao.selectUserById(id);
    }
}
