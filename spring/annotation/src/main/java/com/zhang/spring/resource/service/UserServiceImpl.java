package com.zhang.spring.resource.service;

import com.zhang.spring.resource.dao.UserDao;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "myUserService")
public class UserServiceImpl implements UserService {

    //第二种：通过指定属性名称来注入
    @Resource
    private UserDao myUserDao;

    @Override
    public void add() {
        System.out.println("service.......");
        myUserDao.add();
    }
}
