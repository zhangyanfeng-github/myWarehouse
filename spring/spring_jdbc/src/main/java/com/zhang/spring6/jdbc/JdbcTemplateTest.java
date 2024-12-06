package com.zhang.spring6.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(locations = "classpath:beans.xml")
public class JdbcTemplateTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //测试添加操作
    @Test
    public void testInsert() {
        //第一步  编写sql语句
        String sql = "INSERT INTO t_emp VALUES(NULL,?,?,?)";

        //第二步  调用jdbcTemplate的方法，传入相关参数
        int rows = jdbcTemplate.update(sql, "zhang", 222, "男");
        System.out.println(rows);
    }

    //测试修改操作
    @Test
    public void testUpdate() {
        String sql = "update t_emp set name=? where id=?";
        int rows = jdbcTemplate.update(sql, "骏骏", 1);
        System.out.println(rows);
    }

    //测试删除操作
    @Test
    public void testDelete() {
        String sql = "delete from t_emp where id = ?";
        int rows = jdbcTemplate.update(sql, 3);
        System.out.println(rows);
    }

    //测试查询操作（共有三种情况）
    //查询：返回的是对象
    @Test
    public void testSelectObject() {

        //第一种方法
//        String sql = "select * from t_emp where id=?";
//        Emp empResult = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
//            Emp emp = new Emp();
//            emp.setId(rs.getInt("id"));
//            emp.setName(rs.getString("name"));
//            emp.setAge(rs.getInt("age"));
//            emp.setSex(rs.getString("sex"));
//            return emp;
//        }, 1);
//        System.out.println(empResult);

        //第二种方法
        String sql = "select * from t_emp where id=?";
        Emp emp = jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(Emp.class), 1);
        System.out.println(emp);
    }

    //查询：返回的是List集合（有多个对象）
    @Test
    public void testSelectList() {
        String sql = "select * from t_emp";
        List<Emp> list = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Emp.class));
        System.out.println(list);
    }

    //查询：返回的是单个值（例如查询表中有多少条数据）
    @Test
    public void testSelectValue() {
        String sql = "select count(*) from t_emp";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
    }

}
