package com.zhang.spring.junit.junit5;

import org.springframework.stereotype.Component;

@Component  //创建对象，交给Spring管理
public class User {

    public void run() {
        System.out.println("user........");
    }
}
