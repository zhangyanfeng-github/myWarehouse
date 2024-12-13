package com.zhang.service.impl;

import com.zhang.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.connection.RedisGeoCommands.GeoRadiusCommandArgs;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : 张彦锋
 * Date : 2024/12/13  17:16
 * Text :
 */
@Service
public class GeoServiceImpl implements GeoService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String GEO_KEY = "geo:city";

    /**
     * 添加地理位置
     *
     * @return
     */
    @Override
    public String addGeo() {

        Map<Object, Point> map = new HashMap<>();
        map.put("天安门", new Point(116.403963, 39.915119));
        map.put("故宫", new Point(116.403414, 39.924091));
        map.put("长城", new Point(116.024067, 40.362639));
        map.put("langfang", new Point(116.692524, 39.52622));

        Long add = redisTemplate.opsForGeo().add(GEO_KEY, map);

        return add.toString();
    }

    /**
     * 获取地理位置
     *
     * @param member 要获取的地名
     * @return
     */
    @Override
    public Point getGeoPos(String member) {

        System.out.println("member = " + member);
        List<Point> list = redisTemplate.opsForGeo().position(GEO_KEY, member);
        System.out.println("list = " + list);
        return list.get(0);
    }

    /**
     * 获取地理位置的geohash值
     *
     * @param member 要获取的地名
     * @return
     */
    @Override
    public String getGeoHash(String member) {
        List<String> hash = redisTemplate.opsForGeo().hash(GEO_KEY, member);
        return hash.get(0);
    }

    /**
     * 获取两个地理位置的距离
     *
     * @param member1 地理位置1
     * @param member2 地理位置2
     * @return
     */
    @Override
    public Distance getGeoDist(String member1, String member2) {
        Distance distance = redisTemplate.opsForGeo().
                distance(GEO_KEY, member1, member2, RedisGeoCommands.DistanceUnit.KILOMETERS);
        return distance;
    }

    /**
     * 根据半径获取地理位置，本例写死以天安门为中心
     *
     * @return
     */
    @Override
    public GeoResults resultsByRadius() {
        //通过经纬度查找附近的，以北京天安门为中心，查找附近10KM内,50条数据
        Circle circle = new Circle(new Point(116.403963, 39.915119),
                new Distance(10, RedisGeoCommands.DistanceUnit.KILOMETERS));
        GeoRadiusCommandArgs args =
                GeoRadiusCommandArgs
                        .newGeoRadiusArgs()
                        .includeDistance()
                        .includeCoordinates()
                        .limit(50);
        GeoResults<RedisGeoCommands.GeoLocation<Object>> geoResults =
                redisTemplate.opsForGeo().radius(GEO_KEY, circle, args);
        return geoResults;
    }

    /**
     * 以地名作为中心，上一接口是以经纬度作为中心
     * 获取指定半径内的坐标数据，本例写死以北京天安门附近的地点
     *
     * @return
     */
    @Override
    public GeoResults resultsByRadiusByMember() {
        String member = "天安门";
        Distance distance = new Distance(10, RedisGeoCommands.DistanceUnit.KILOMETERS);
        GeoRadiusCommandArgs args =
                GeoRadiusCommandArgs
                        .newGeoRadiusArgs()
                        .includeDistance()
                        .includeCoordinates()
                        .limit(50);
        GeoResults<RedisGeoCommands.GeoLocation<Object>> geoResults =
                redisTemplate.opsForGeo().radius(GEO_KEY, member, distance, args);
        return geoResults;
    }
}
