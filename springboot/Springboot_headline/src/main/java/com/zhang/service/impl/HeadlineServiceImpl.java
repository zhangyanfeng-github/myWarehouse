package com.zhang.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.pojo.Headline;
import com.zhang.pojo.vo.PortalVo;
import com.zhang.service.HeadlineService;
import com.zhang.mapper.HeadlineMapper;
import com.zhang.utils.JwtHelper;
import com.zhang.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张彦锋
 * @description 针对表【news_headline】的数据库操作Service实现
 * @createDate 2024-11-16 10:35:32
 */
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
        implements HeadlineService {

    @Autowired
    private HeadlineMapper headlineMapper;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 首页数据查询
     * <p>
     * 1、进行分页数据查询
     * 2、将查询到的数据拼接到result中
     * <p>
     * 注意：1、需要自定义查询语句，自定义Mapper方法，携带分页参数
     * 2、返回的结果拼接到List<Map>中
     */
    @Override
    public Result findNewsPage(PortalVo portalVo) {

        IPage<Map> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());
        headlineMapper.selectMyPage(page, portalVo);

        List<Map> records = page.getRecords();
        Map data = new HashMap();
        data.put("pageData", records);
        data.put("pageNum", page.getCurrent());
        data.put("pageSize", page.getSize());
        data.put("totalPage", page.getPages());
        data.put("totalSize", page.getTotal());

        Map pageInfo = new HashMap();
        pageInfo.put("pageInfo", data);

        return Result.ok(pageInfo);
    }


    /**
     * 1、查询对应的数据即可，（多表查询，头条表和用户表，自定义方法，返回Map类型）
     * 2、修改阅读量 + 1
     *
     * @param hid
     * @return
     */
    @Override
    public Result showHeadlineDetail(Integer hid) {
        Map data = headlineMapper.queryDetailMap(hid);

        Map headlineMap = new HashMap();
        headlineMap.put("headline", data);

        //修改阅读量
        Headline headline = new Headline();
        headline.setHid((Integer) data.get("hid"));
        headline.setVersion((Integer) data.get("version"));

        //阅读量 + 1
        headline.setPageViews((Integer) data.get("pageViews") + 1);
        headlineMapper.updateById(headline);

        return Result.ok(headlineMap);
    }

    /**
     * 发布头条方法，需要补全数据
     *
     * @param headline
     * @param token
     * @return
     */
    @Override
    public Result publish(Headline headline, String token) {

        //通过token查询用户的ID
        int userId = jwtHelper.getUserId(token).intValue();

        //数据装配
        headline.setPublisher(userId);
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());

        headlineMapper.insert(headline);

        return Result.ok(null);
    }

    /**
     * 修改数据
     * <br/>
     * 1、根据hid查询最新的版本号
     * 2、再修改数据的修改时间为当前节点
     *
     * @param headline
     * @return
     */
    @Override
    public Result updateData(Headline headline) {

        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();
        headline.setVersion(version);
        headline.setUpdateTime(new Date());

        headlineMapper.updateById(headline);

        return Result.ok(null);
    }
}




