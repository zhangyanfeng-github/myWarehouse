package com.zhang.controller;

import com.zhang.service.GuavaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : 张彦锋
 * Date : 2024/12/15  18:18
 * Text :
 */
@RestController
@RequestMapping("guava")
public class GuavaController {

    @Autowired
    private GuavaService guavaService;

    @GetMapping
    public void guavaBloomFilter(){
        guavaService.guavaBloomFilter();
    };
}
