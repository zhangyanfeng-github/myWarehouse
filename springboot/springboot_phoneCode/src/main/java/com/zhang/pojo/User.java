package com.zhang.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    private String name;
    private String phone;
    private String code;
}
