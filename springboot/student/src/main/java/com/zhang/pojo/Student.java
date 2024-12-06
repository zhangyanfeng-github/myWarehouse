package com.zhang.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Student {

    private int id;
    private String name;
    private int age;

}
