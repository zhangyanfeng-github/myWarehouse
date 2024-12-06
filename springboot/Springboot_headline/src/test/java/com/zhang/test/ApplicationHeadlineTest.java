package com.zhang.test;

import com.zhang.mapper.HeadlineMapper;
import com.zhang.mapper.TypeMapper;
import com.zhang.mapper.UserMapper;
import com.zhang.utils.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationHeadlineTest {

    @Autowired
    private HeadlineMapper headlineMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test_Headline() {
        headlineMapper.selectList(null);
    }

    @Test
    public void test_Type() {
        typeMapper.selectList(null);
    }

    @Test
    public void test_User() {
        userMapper.selectList(null);
    }

    @Test
    public void test(){
        //生成 传入用户标识
        String token = jwtHelper.createToken(1L);
        System.out.println("token = " + token);

        //解析用户标识
        int userId = jwtHelper.getUserId(token).intValue();
        System.out.println("userId = " + userId);

        //校验是否到期! false 未到期 true到期
        boolean expiration = jwtHelper.isExpiration(token);
        System.out.println("expiration = " + expiration);
    }
}
