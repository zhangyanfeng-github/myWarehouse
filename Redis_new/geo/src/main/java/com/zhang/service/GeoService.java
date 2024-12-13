package com.zhang.service;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;

import org.springframework.data.geo.Point;

/**
 * Author : 张彦锋
 * Date : 2024/12/13  17:15
 * Text :
 */
public interface GeoService {
    String addGeo();

    Point getGeoPos(String member);

    String getGeoHash(String member);

    Distance getGeoDist(String member1, String member2);

    GeoResults resultsByRadius();

    GeoResults resultsByRadiusByMember();
}
