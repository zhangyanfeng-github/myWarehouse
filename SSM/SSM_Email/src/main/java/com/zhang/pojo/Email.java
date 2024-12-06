package com.zhang.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Email {

    private String saveName;
    private String title;
    private String content;

}
