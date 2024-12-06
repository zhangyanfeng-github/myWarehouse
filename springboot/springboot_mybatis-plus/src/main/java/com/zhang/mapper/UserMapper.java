package com.zhang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    //传入的User表示的是实体类，采用的方法就是通过实体类的类名和数据库的表名保持一致实现的
    //此时UserMapper就已经 获取到了user表的增删改查方法
    //或者加入TableName注解来指定


    //MyBaits中的定义方法位置，BaseMapper已经实现好了，直接调用即可，对于单表来说

    //自定义方法，实现相关查询功能
    //    //定义一个根据年龄参数查询，并且分页的方法，条件age > ?
    IPage<User> queryByAge(IPage<User> page, @Param("age") Integer age);
}
