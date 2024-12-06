package com.zhang.service;

import com.zhang.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhang.utils.Result;

/**
* @author 张彦锋
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-11-16 10:35:32
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();
}
