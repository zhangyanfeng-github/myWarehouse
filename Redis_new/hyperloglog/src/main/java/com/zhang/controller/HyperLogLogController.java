package com.zhang.controller;

import com.zhang.service.HyperLogLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : 张彦锋
 * Date : 2024/12/13  15:34
 * Text :
 */
@RestController
@RequestMapping("hyperLogLog")
public class HyperLogLogController {

    @Autowired
    private HyperLogLogService hyperLogLogService;

    @GetMapping
    public Long hyperLogLog() {
        return hyperLogLogService.uv();
    }

}
