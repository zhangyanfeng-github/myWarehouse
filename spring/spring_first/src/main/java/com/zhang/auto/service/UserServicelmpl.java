package com.zhang.auto.service;

import com.zhang.auto.dao.UserDao;

public class UserServicelmpl implements UserService{
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUserService() {
        System.out.println("userService中的方法执行了。。。");
        userDao.addUserDao();
    }
}
