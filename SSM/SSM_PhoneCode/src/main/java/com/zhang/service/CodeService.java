package com.zhang.service;

import com.zhang.pojo.User;

public interface CodeService {

    String sendCode(User user);

    Integer addUser(String name, String phone, String code);
}
