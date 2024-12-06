package com.zhang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhang.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author 张彦锋
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2024-11-15 20:40:19
 * @Entity com.zhang.pojo.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    //通过相同的age查询表中所有信息
    List<User> selectAllByAge(@Param("age") Integer age);

    //
}




