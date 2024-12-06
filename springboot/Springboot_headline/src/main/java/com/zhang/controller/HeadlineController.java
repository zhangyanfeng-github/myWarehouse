package com.zhang.controller;

import com.zhang.pojo.Headline;
import com.zhang.service.HeadlineService;
import com.zhang.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadlineController {

    @Autowired
    private HeadlineService headlineService;

    //需要检查token是否有效，所以配置一个只要调用headline中方法就生效的拦截器，只要失效就拦截
    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader String token) {
        Result result = headlineService.publish(headline, token);
        return result;
    }

    /**
     * 修改回显功能
     */
    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(Integer hid) {
        Headline headline = headlineService.getById(hid);
        Map data = new HashMap();
        data.put("headline", headline);

        return Result.ok(data);
    }

    /**
     * 修改数据
     */
    @PostMapping("update")
    public Result update(@RequestBody Headline headline) {
        Result result = headlineService.updateData(headline);
        return result;
    }

    /**
     * 删除
     */
    @PostMapping("removeByHid")
    public Result removeByHid(Integer hid) {
        headlineService.removeById(hid);
        return Result.ok(null);
    }
}
