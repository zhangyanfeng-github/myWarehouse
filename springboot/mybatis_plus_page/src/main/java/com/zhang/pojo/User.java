package com.zhang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName t_user
 */
//@TableName(value ="t_user")
@Data
public class User implements Serializable {
    private Integer uid;

    private String userName;

    private Integer userAge;

    private String userPhone;

    private String userEmail;

    private Integer version;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}