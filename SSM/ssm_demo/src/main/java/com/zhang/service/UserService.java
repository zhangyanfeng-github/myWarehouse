package com.zhang.service;

import com.zhang.pojo.User;
import com.zhang.utils.R;

import java.util.List;

public interface UserService {

    R page(int pageSize, int currentPage);

    R remove(Integer id);

    R save(User user);

    R update(User user);
}
