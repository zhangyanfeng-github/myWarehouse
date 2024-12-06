package com.zhang.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
// @TableName("t_user")
/**
 * @TableName 可以不加，默认使用实体类的名字作为表名，并且忽略大小写
 * 当数据库的表名和实体类的类名不同时（忽略大小写）、使用@TableName注解指定表名
 * 一般情况下会在properties文件中指定表的前缀，因为数据库中的表会加上类似 t_XXXX 的前缀
 */

public class User {


    /**
     * 默认： 雪花算法：要求 ：1 数据库主键的数据类型必须是bigint / varchar(64)
     * 2 实体类必须是Long类型
     * 3 随机生成一个数字给主键赋值，不会重复
     * auto：1 MySQL中的主键必须是数字，而且设置 auto_increment 自增属性
     */

//  @TableId(type = IdType.AUTO)    //设置主键自增，前提是MySQL数据库中的主键也是自增
    private Long id;

    @TableField(value = "name", exist = true)   //属性名和列名对应，当表中有这个列，那就是true，没有就是false
    private String name;
    private Integer age;
    private String email;


    /**
     * 当前属性对应的列就是逻辑删除的状态字段
     * 当删除数据的时候，自动变成修改此列的属性值，默认：0是未删除， 1是删除
     * 当查询数据的时候，默认只查询deleted = 0 的数据。
     */
    @TableLogic
    private Integer deleted;

    //添加乐观锁版本号属性
    @Version
    private Integer version;

}
