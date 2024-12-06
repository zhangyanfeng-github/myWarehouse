package com.zhang.spring.autowired.service;

import com.zhang.spring.autowired.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    //注入Dao
    //第一种方式，通过属性注入
//    @Autowired      //根据类型找到对应对象，完成注入（属性注入方式）
//    private UserDao userDao;


    //第二种方式，通过set方法注入
//    private UserDao userDao;
//
//    @Autowired
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }

    //第三种方式，通过构造方法注入
//    private UserDao userDao;
//
//    @Autowired
//    public UserServiceImpl(UserDao userDao) {
//        this.userDao = userDao;
//    }

    //第四种方式，通过形参上的注入（同样生成构造方法）
//    private UserDao userDao;
//
//    public UserServiceImpl(@Autowired UserDao userDao) {
//        this.userDao = userDao;
//    }

    //第五种方式，只有一个有参数构造函数，无注解
//    private UserDao userDao;
//
//    public UserServiceImpl(UserDao userDao) {
//        this.userDao = userDao;
//    }

    //第六种方式，使用两个注解，根据名称注入（UserDao有两个实现类，现要注入另一个实现类Redis）
    @Autowired
    @Qualifier(value = "userRedisDaoImpl")      //默认首字母小写
    private UserDao userDao;


    @Override
    public void add() {
        System.out.println("service.......");
        userDao.add();
    }
}
