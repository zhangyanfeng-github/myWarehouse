package com.zhang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhang.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhang.pojo.vo.PortalVo;
import com.zhang.utils.Result;

import java.util.Map;

/**
* @author 张彦锋
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-11-16 10:35:32
*/
public interface HeadlineService extends IService<Headline> {

    /**
     * 首页数据查询
     * @param portalVo
     * @return
     */
    Result findNewsPage(PortalVo portalVo);

    /**
     * 根据ID查询详情
     * @param hid
     * @return
     */
    Result showHeadlineDetail(Integer hid);

    Result publish(Headline headline, String token);

    Result updateData(Headline headline);
}
