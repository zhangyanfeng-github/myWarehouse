package com.zhang.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.pojo.User;
import com.zhang.service.UserService;
import com.zhang.mapper.UserMapper;
import com.zhang.utils.JwtHelper;
import com.zhang.utils.MD5Util;
import com.zhang.utils.Result;
import com.zhang.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张彦锋
 * @description 针对表【news_user】的数据库操作Service实现
 * @createDate 2024-11-16 10:35:32
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 登录业务
     * 1、根据账号查询用户对象loginUser
     * 2、如果用户对象为null，说明没有此账号，返回501错误
     * 3、如果不为空，说明有，那么对比密码，失败返回503
     * 4、账号密码都正确。根据用户ID生成一个token，把token返回给前端
     *
     * @param user 前端传来的json数据
     * @return 返回查询结果
     */
    @Override
    public Result login(User user) {

        //根据账号查询数据库中是否存在此账号
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());

        //将查询到的结果保存在loginUser中
        User loginUser = userMapper.selectOne(lambdaQueryWrapper);

        //判断loginUser是否为空
        if (loginUser == null) {
            return Result.build(null,ResultCodeEnum.USERNAME_ERROR);
        }

        //当loginUser不为空，说明存在此用户，那么对比密码
        if (!StringUtils.isEmpty(user.getUserPwd())
            && MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())){

            //登录成功后，根据用户ID生成token
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));

            //将token封装到result中返回
            Map data = new HashMap();
            data.put("token",token);

            return Result.ok(data);
        }

        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);

    }

    /**
     * 根据token来获取用户的数据
     * 1、首先判断token是否在有效期内，失效直接返回状态码
     * 2、再根据token来解析userId
     * 3、根据用户的ID查用户的数据
     * 4、把密码去掉，封装result结果返回
     *
     * @param token
     * @return
     */
    @Override
    public Result getUserInfo(String token) {

        //判断token是否有效 true表示过期
        boolean expiration = jwtHelper.isExpiration(token);

        if (expiration) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        //根据token来解析用户ID
        int userId = jwtHelper.getUserId(token).intValue();

        //根据用户ID查询用户数据
        User user = userMapper.selectById(userId);

        //将用户密码设为空字符串
        user.setUserPwd("");

        //封装返回result结果
        Map data = new HashMap();
        data.put("loginUser", user);

        return Result.ok(data);
    }

    /**
     * 用户注册功能
     * 1、检查用户是否被注册
     * 2、密码加密处理
     * 3、账号数据保存
     * 4、返回结果
     *
     * @param user
     * @return
     */
    @Override
    public Result regist(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());

        Long count = userMapper.selectCount(lambdaQueryWrapper);

        if (count > 0) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));

        userMapper.insert(user);

        return Result.ok(null);
    }

    /**
     * 检查账号是否可用
     * 1、根据账号进行count查询
     * 2、count == 0 说明可用
     * count == 1 不可用
     *
     * @param username
     * @return
     */
    @Override
    public Result checkUserName(String username) {

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, username);

        Long count = userMapper.selectCount(lambdaQueryWrapper);

        if (count == 0) {
            return Result.ok(null);
        }
        return Result.build(null, ResultCodeEnum.USERNAME_USED);
    }


}




