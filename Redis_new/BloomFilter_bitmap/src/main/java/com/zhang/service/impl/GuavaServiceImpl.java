package com.zhang.service.impl;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.zhang.service.GuavaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Author : 张彦锋
 * Date : 2024/12/15  18:19
 * Text :
 */
@Service
@Slf4j
public class GuavaServiceImpl implements GuavaService {

    /**
     * 需求：将100W条白名单数据保存到BloomFilter中，并用另外的10W条数据查询，判断数据是否合法
     */

    //定义一个常量
    public static final int _1W = 10000;
    //定义BloomFilter初始容量
    public static final int SIZE = 100 * _1W;
    //误判率，越小表示误判的个数也就越小，一般是0.03，如果精度过高，所消耗的资源也就越多
    public static double fpp = 0.03;
    //创建Guava布隆过滤器
    private static BloomFilter<Integer> bloomFilter =
            BloomFilter.create(Funnels.integerFunnel(), SIZE, fpp);

    @Override
    public void guavaBloomFilter() {
        //初始化100W条数据，将100W条数据保存到布隆过滤器中
        for (int i = 0; i < SIZE; i++) {
            bloomFilter.put(i);
        }
        //判断10W条数据是否合法，保存到列表中
        ArrayList<Integer> list = new ArrayList<>(10 * _1W);
        //验证
        for (int i = SIZE + 1; i < SIZE + 10 * _1W + 1; i++) {
            if (bloomFilter.mightContain(i)) {
                log.info("被误判了：{}", i);
                list.add(i);
            }
        }
        log.info("误判的个数：{}", list.size());
    }
}
