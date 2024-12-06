package com.zhang.mybatis.mapper;

import com.zhang.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SQLMapper {

    /**
     * 根据用户名模糊查询用户信息（同一个用户名可能有多条数据）
     */
    List<User> getUserByLike(@Param("username")String username);

    /**
     * 根据ID实现批量删除
     */
    int deleteMore(@Param("ids") String ids);

    /**
     * 查询指定表中的数据
     */
    List<User> getUserByTableName(@Param("tablename")String tablename);
}
