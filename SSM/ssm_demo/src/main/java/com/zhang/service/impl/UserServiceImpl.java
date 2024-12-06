package com.zhang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.mapper.UserMapper;
import com.zhang.pojo.User;
import com.zhang.service.UserService;
import com.zhang.utils.PageBean;
import com.zhang.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public R page(int pageSize, int currentPage) {
        //分页，会自动在sql语句后加入limit，所以在xml中的sql语句不能加封号
        PageHelper.startPage(currentPage, pageSize);

        //查询（查询全部即可，因为有分页插件，会根据传入的参数自动分配页数）
        List<User> userList = userMapper.selectAll();

        //分页数据装配
        PageInfo<User> info = new PageInfo<>(userList);

        //装配PageBean
        PageBean<User> pageBean = new PageBean<>(currentPage, pageSize, info.getTotal(), info.getList());

        R ok = R.ok(pageBean);
        return ok;

    }

    @Override
    public R remove(Integer id) {
        int rows = userMapper.deleteUserById(id);

        if (rows > 0) {
            return R.ok(null);
        }

        return R.fail(null);
    }

    @Override
    public R save(User user) {
        int rows = userMapper.insert(user);

        if (rows > 0) {
            return R.ok(null);
        }

        return R.fail(null);
    }

    @Override
    public R update(User user) {
        //判断ID不能为null
        if (user.getId() == null){
            return R.fail("ID为空，无法修改");
        }

        int rows = userMapper.update(user);
        if (rows > 0) {
            return R.ok(null);
        }

        return R.fail(null);
    }
}
