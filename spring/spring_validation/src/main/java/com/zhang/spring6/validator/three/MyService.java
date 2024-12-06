package com.zhang.spring6.validator.three;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated  //表示基于方法实现校验
public class MyService {

    public String testMethod(@NotNull @Valid User user) {
        return user.toString();
    }
}
