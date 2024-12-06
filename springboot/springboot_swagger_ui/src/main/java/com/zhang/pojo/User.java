package com.zhang.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;

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
}