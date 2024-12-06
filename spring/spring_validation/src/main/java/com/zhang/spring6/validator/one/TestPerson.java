package com.zhang.spring6.validator.one;

import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

public class TestPerson {

    public static void main(String[] args) {

        //创建Person对象
        Person person = new Person();
        person.setName("feng");
        person.setAge(-11);

        //创建person对应的databinder
        DataBinder binder = new DataBinder(person);

        //设置校验器
        binder.setValidator(new PersonValidator());

        //调用方法执行校验
        binder.validate();

        //输出校验结果
        BindingResult result = binder.getBindingResult();
        System.out.println(result.getAllErrors());
    }
}
