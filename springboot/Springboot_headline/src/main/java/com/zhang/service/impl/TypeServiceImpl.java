package com.zhang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.pojo.Type;
import com.zhang.service.TypeService;
import com.zhang.mapper.TypeMapper;
import com.zhang.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张彦锋
 * @description 针对表【news_type】的数据库操作Service实现
 * @createDate 2024-11-16 10:35:32
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
        implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 查询全部类别信息
     *
     * @return
     */
    @Override
    public Result findAllTypes() {

        List<Type> types = typeMapper.selectList(null);

        return Result.ok(types);
    }

}




