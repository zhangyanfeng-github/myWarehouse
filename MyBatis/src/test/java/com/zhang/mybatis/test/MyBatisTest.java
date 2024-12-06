package com.zhang.mybatis.test;

import com.zhang.mybatis.mapper.UserMapper;
import com.zhang.mybatis.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhang.mybatis.utils.SqlSessionUtils.getSqlSession;

/**
 * 测试MyBatis执行SQL语句
 */
public class MyBatisTest {

    @Test
    public void testMyBatis() {

        //getSqlSession();是已经封装好的工具类
        SqlSession sqlSession = getSqlSession();

        //获取mapper接口对象  //自定义接口名UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //测试功能
        int result = mapper.insertUser();

        System.out.println("result：" + result);
    }

    @Test
    public void testUpdate() {

        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUser();
    }

    @Test
    public void testDelete() {

        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUser();
    }

    /**
     * 测试查询的相应功能
     */
    @Test
    public void testGetInfo() {

        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //查询全部信息用List，存放在集合中
        //lambda表达式，循环遍历表中信息，依次输出
        List<User> list = mapper.getAllUser();
        list.forEach(user -> System.out.println(user));
    }

    @Test
    public void testGetUserById() {
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //查询一条数据用User
        User user = mapper.getUserById(2);
        System.out.println(user);
    }

    @Test
    public void testCheckLogin() {
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.CheckLogin("张三", "123456");
        System.out.println(user);
    }

    @Test
    public void testCheakLoginByMap() {
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("username", "admin");
        map.put("password", "123456");
        User user = mapper.CheckLoginByMap(map);
        System.out.println(user);
    }

    @Test
    public void testInsertUser() {
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int result = mapper.insertUserByClass(new User(null, "admin", "111111", 22, "男", "123@qq.com"));
        System.out.println(result);
    }

    @Test
    public void testCheckLoginByParam(){
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.CheckLoginByParam("admin","123456");
        System.out.println(user);
    }
}
