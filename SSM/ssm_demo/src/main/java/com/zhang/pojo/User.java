package com.zhang.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    private Integer Id;
    @NotNull
    private String title;
    @NotNull
    private boolean completed;

}
