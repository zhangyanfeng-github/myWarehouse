package com.zhang.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.pojo.User;
import com.zhang.service.UserService;
import com.zhang.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张彦锋
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2024-11-19 15:33:39
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 分页查询
     * @param current   页数
     * @param size  页尺寸
     * @return
     */
    @Override
    public Object getUserPage(Integer current, Integer size) {

        IPage<User> page = new Page<>(current, size);
        return userMapper.selectPage(page, null);
    }
}




