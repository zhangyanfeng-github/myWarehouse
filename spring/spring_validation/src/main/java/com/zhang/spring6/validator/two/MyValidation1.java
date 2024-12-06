package com.zhang.spring6.validator.two;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
//校验器的第一种写法
public class MyValidation1 {

    @Autowired
    private Validator validator;

    public boolean validatorByUserOne(User user) {
        Set<ConstraintViolation<User>> validate = validator.validate(user);
        return validate.isEmpty();
    }
}
