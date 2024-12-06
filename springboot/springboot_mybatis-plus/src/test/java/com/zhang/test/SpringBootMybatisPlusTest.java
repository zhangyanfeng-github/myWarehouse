package com.zhang.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.mapper.UserMapper;
import com.zhang.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class SpringBootMybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test_select() {
        //查询一个id
        User user = userMapper.selectById(1);
        System.out.println("user = " + user);

        //查询多个id
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(3L);
        List<User> users = userMapper.selectBatchIds(ids);
        System.out.println("users = " + users);


    }

    @Test
    public void test_insert() {

        User user = new User();
        user.setAge(99);
        user.setName("张彦锋");
        user.setEmail("2e2321313");

        int rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void test_update() {
        //例如，将id = 1的age改为90
        User user = new User();
        user.setId(1L);
        user.setAge(90);

        int rows = userMapper.updateById(user);
        System.out.println("rows = " + rows);

    }

    @Test
    public void test_delete() {
        //根据id删除
        int rows = userMapper.deleteById(1);
        System.out.println("rows = " + rows);

        //根据age = ? 并且id = ? 删除记录
        Map param = new HashMap();
        param.put("age", 99);
        param.put("id", 1857022401502609409L);

        int i = userMapper.deleteByMap(param);
        System.out.println("i = " + i);

        //wrapper 条件封装对象，无限的封装条件
        //userMapper.delete(wrapper);
    }

    //测试分页查询功能
    @Test
    public void test_page() {

        //设置分页参数
        Page<User> page = new Page<>(1, 3);
        userMapper.selectPage(page, null);

        //page最后会被封装成结果返回
        long current = page.getCurrent();  //返回页码
        long size = page.getSize();     //返回页容量
        List<User> records = page.getRecords();     //当前页的数据
        long total = page.getTotal();       //总条数

        System.out.println("current = " + current);
        System.out.println("size = " + size);
        System.out.println("records = " + records);
        System.out.println("total = " + total);
    }

    /**
     * 测试自定义方法，实现分页查询功能
     */
    @Test
    public void test_MyPage() {
        //设置分页参数
        Page<User> page = new Page<>(1, 3);
        userMapper.queryByAge(page, 1);

        //page最后会被封装成结果返回
        long current = page.getCurrent();  //返回页码
        long size = page.getSize();     //返回页容量
        List<User> records = page.getRecords();     //当前页的数据
        long total = page.getTotal();       //总条数

        System.out.println("current = " + current);
        System.out.println("size = " + size);
        System.out.println("records = " + records);
        System.out.println("total = " + total);
    }

    /**
     * 测试使用QueryWrapper用代码实现sql语句的拼接
     */
    @Test
    public void test_01() {
        //查询用户名包含 a 的：like，年龄在20-30，并且邮箱不为空的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //添加条件，动态的调用Wrapper的方法完成拼接即可
        queryWrapper.like("name", "a");
        queryWrapper.between("age", 20, 30);
        queryWrapper.isNotNull("email");

        //或者链式调用
        queryWrapper.like("name", "a")
                .between("age", 20, 30)
                .isNotNull("email");

        //转化为sql语句：select * from user where name like "%a%" and age >= 20
        //             and age <= 30 and email is not null;

        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println("users = " + users);
    }

    /**
     * 按照年龄降序查询用户，如果年龄相同则按照ID升序排列
     */
    @Test
    public void test_02() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("id");

        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println("users = " + users);
    }

    /**
     * 删除Email为空的用户
     */
    @Test
    public void test_03() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");

        userMapper.delete(queryWrapper);
    }

    /**
     * 将年龄大于20并且用户名中包含 a 或邮箱为null的用户信息修改
     */
    @Test
    public void test_04() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 20)
                .like("name", "a")
                .or().isNull("email");

        User user = new User();
        user.setAge(10);
        user.setName("10岁男孩");
        user.setEmail("11111");

        userMapper.update(user, queryWrapper);
    }

    /**
     * 查询用户信息的name和age字段，并且ID > 1
     */
    @Test
    public void test_05() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.gt("id", 1L);

        //默认查询的是全部列，用select指定列
        queryWrapper.select("name", "age");

        userMapper.selectList(queryWrapper);
    }

    /**
     * 假如前端传入了两个参数 name 和 age。
     * 要求name不为空，age > 18 作为条件判断
     */
    @Test
    public void test_06() {
        String name = "xxx";
        Integer age = 19;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        //每个方法都会有自动判断的 eq方法
        queryWrapper.eq(StringUtils.isNotBlank(name), "name", name);
        queryWrapper.eq(age != null && age > 18, "age", age);

        userMapper.selectList(queryWrapper);

    }

    /**
     * 测试UpdateWrapper的使用方法
     */
    @Test
    public void test_UpdateWrapper() {
        //对比QueryWrapper。修改时需要准备要修改的实体类数据，而UpdateWrapper不需要准备实体类对象
        //QueryWrapper不能设置字段为null，而UpdateWrapper可以设为null
        //直接传入查询条件，并把查询到的数据进行修改

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.gt("age", 20)
                .like("name", "a")
                .or().isNull("email")
                .set("email", null)
                .set("age", 90);

        userMapper.update(null, updateWrapper);
    }

    /**
     * 测试lambdaQueryWrapper的用法
     */
    @Test
    public void test_LambdaQueryWrapper() {
        //为了防止列名对应不上，所以引入了LambdaQueryWrapper，函数式编程，直接引入列名
        //QueryWrapper的写法
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //链式调用
        queryWrapper.like("name", "a")
                .between("age", 20, 30)
                .isNotNull("email");

        //LambdaQueryWrapper的写法
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName, "a")
                .between(User::getAge, 20, 30)
                .isNotNull(User::getEmail);

        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        System.out.println("users = " + users);
    }

    /**
     * 测试LambdaUpdateWrapper的用法
     */
    @Test
    public void test_LambdaUpdateWrapper() {
        //与LambdaQueryWrapper类似
        //UpdateWrapper的写法
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.gt("age", 20)
                .like("name", "a")
                .or().isNull("email")
                .set("email", null)
                .set("age", 90);

        //LambdaUpdateWrapper的写法
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.gt(User::getAge, 20)
                .like(User::getName, "a")
                .or().isNull(User::getEmail)
                .set(User::getEmail, null)
                .set(User::getAge, 90);

        Object result = userMapper.update(lambdaUpdateWrapper);
        System.out.println("result = " + result);
    }

    /**
     * 查询全部信息，
     */
    @Test
    public void test_seleteAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println("users = " + users);
    }

    /**
     * 演示乐观锁生效场景
     */
    @Test
    public void test_Optimistic_Locking() {
        //先查询，获取version数据
        User user = userMapper.selectById(5);
        User user1 = userMapper.selectById(5);

        //修改数据
        user.setAge(20);
        user1.setAge(30);

        userMapper.updateById(user);     //修改完成后，将version修改为2
        userMapper.updateById(user1);   //此时version = 1,!= 2,所以修改失败

        //最后的结果会是20，ID=5的字段version = 2
    }

    /**
     * 测试全表删除或更新的拦截是否成功
     */
    @Test
    public void test_deleteAll() {
        //null默认全部数据
        //会抛异常Error updating database.
        // Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException:
        // Prohibition of table update operation
        userMapper.delete(null);
    }

}
