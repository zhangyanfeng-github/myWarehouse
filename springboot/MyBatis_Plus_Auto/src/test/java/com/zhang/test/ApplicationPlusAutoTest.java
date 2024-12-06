package com.zhang.test;

import com.zhang.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationPlusAutoTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectAllByAge() {
        userMapper.selectAllByAge(20);
    }
}
