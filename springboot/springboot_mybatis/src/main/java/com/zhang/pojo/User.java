package com.zhang.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data       //表示会自动添加get。set方法，不用手动添加
@Component  //把实体类加入到Bean中
public class User {

    private int id;
    private String name;
    private String passwd;
}
