package com.zhang.controller;

import com.zhang.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.geo.Point;

/**
 * Author : 张彦锋
 * Date : 2024/12/13  17:15
 * Text :
 */
@RestController
@RequestMapping("geo")
public class GeoController {

    @Autowired
    private GeoService geoService;

    /**
     * 添加geo坐标数据
     *
     * @return
     */
    @GetMapping("addGeo")
    public String addGeo() {
        return geoService.addGeo();
    }

    /**
     * 获取geo坐标数据
     *
     * @param member 要获取的地名
     * @return
     */
    @GetMapping("getGeoPos")
    public Point getGeoPos(String member) {
        return geoService.getGeoPos(member);
    }

    /**
     * 获取经纬度生成的base32编码值geohash
     *
     * @param member
     * @return
     */
    @GetMapping("getGeoHash")
    public String getGeoHash(String member) {
        return geoService.getGeoHash(member);
    }

    /**
     * 获取两个坐标之间的距离
     *
     * @param member1
     * @param member2
     * @return
     */
    @GetMapping("getGeoDist")
    public Distance getGeoDist(String member1, String member2) {
        return geoService.getGeoDist(member1, member2);
    }

    /**
     * 获取指定半径内的坐标数据，本例写死以北京天安门附近的地点
     * 以地点的经纬度做坐标数据
     * @return
     */
    @GetMapping("getGeoRadius")
    public GeoResults resultsByRadius() {
        return geoService.resultsByRadius();
    }

    /**
     * 以地名作为中心，上一接口是以经纬度作为中心
     * 获取指定半径内的坐标数据，本例写死以北京天安门附近的地点
     * @return
     */
    @GetMapping("getGeoRadiusByMember")
    public GeoResults resultsByRadiusByMember() {
        return geoService.resultsByRadiusByMember();
    }

}

