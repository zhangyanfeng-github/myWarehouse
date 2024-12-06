package com.zhang.mybatis.mapper;

import com.zhang.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /**
     * MyBatis面向接口编程的两个一致：
     * 1、映射文件的namespace要和mapper接口的全类名保持一致
     * 2、映射文件中的SQL语句的id要和mapper接口中的方法名一致
     */

    /**
     * 模拟检查登录（使用MyBatis已经定义好的Map）
     */
    User CheckLogin(String username, String password);

    /**
     * 模拟检查登录，用自定义Map的形式（常用）
     */
    User CheckLoginByMap(Map<String , Object> map);

    /**
     * 模拟检查登录，用@param注解的方式
     */
    User CheckLoginByParam(@Param("username")String username,@Param("password")String password);

    /**
     * 创建添加用户信息的方法。（固定的sql语句，只能添加已经编辑好的用户信息，固定一种）
     * 通过调用方法，去对应的映射文件中执行相关的SQL语句
     */
    int insertUser();


    /**
     * 添加用户信息，以传入参数的方法来执行可变的SQL语句（常用）
     */
    int insertUserByClass(User user);


    /**
     * 创建修改用户信息方法
     */
    void updateUser();

    /**
     * 创建删除用户信息方法
     */
    void deleteUser();

    /**
     * 查询一条用户信息
     */
    User getUserById(int id);

    /**
     * 查询全部用户信息
     */
    List<User> getAllUser();
}
