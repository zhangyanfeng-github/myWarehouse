package com.zhang.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {

    private int id;
    private String user;
    private String passwd;
}
