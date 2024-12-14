package com.zhang.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.Data;

/**
 * @TableName t_user
 */
//@TableName(value ="t_user")
@Data
public class User implements Serializable {
    @TableId
    private Integer uid;

    private String userName;

    private Integer userAge;

    private String userPhone;

    private String userEmail;

    @Version
    private Integer version;

    @TableLogic
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", version=" + version +
                ", isDeleted=" + isDeleted +
                '}';
    }
}