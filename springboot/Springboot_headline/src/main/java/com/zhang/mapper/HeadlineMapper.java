package com.zhang.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhang.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhang.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author 张彦锋
 * @description 针对表【news_headline】的数据库操作Mapper
 * @createDate 2024-11-16 10:35:32
 * @Entity com.zhang.pojo.Headline
 */
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map> selectMyPage(IPage page, @Param("portalVo") PortalVo portalVo);

    Map queryDetailMap(Integer hid);
}




