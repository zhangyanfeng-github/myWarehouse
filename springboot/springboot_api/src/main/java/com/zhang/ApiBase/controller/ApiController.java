package com.zhang.ApiBase.controller;

import com.zhang.ApiBase.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : 张彦锋
 * Date : 2024/11/30  14:15
 * Text :
 */
@RestController
@RequestMapping("weather")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping
    public Object getWeather(){
        return apiService.getWeather();
    }
}
