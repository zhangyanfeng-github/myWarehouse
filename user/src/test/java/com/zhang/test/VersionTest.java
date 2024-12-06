package com.zhang.test;

/**
 * Author : 张彦锋
 * Date : 2024/11/22  20:24
 * Text : 创建测试类
 */

import com.zhang.mapper.UserMapper;
import com.zhang.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * The type Version test.
 */
@SpringBootTest
public class VersionTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * Test version.
     */
    @Test
    public void test_version() {

        User user = userMapper.selectById(6);       //user 和user1 获取的版本号version都是1
        User user1 = userMapper.selectById(6);

        user.setUserAge(30);    //以第一个为准
        user1.setUserAge(40);

        userMapper.updateById(user);    //user修改完，version + 1 此时version是2
        userMapper.updateById(user1);   //user1修改时，version = 1 ！= 2 所以不让修改
    }

    @Test
    public void test() {
        System.out.println("hello");
        int a = 11;
        int b = 12;

        System.out.println(a + b);
    }
}
