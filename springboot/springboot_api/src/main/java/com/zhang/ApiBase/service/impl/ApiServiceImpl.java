package com.zhang.ApiBase.service.impl;

import com.zhang.ApiBase.service.ApiService;
import com.zhang.ApiBase.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : 张彦锋
 * Date : 2024/11/30  14:16
 * Text :
 */
@Service
public class ApiServiceImpl implements ApiService {

    @Override
    public Object getWeather() {
        //远程调用阿里云API，实现天气的查询

        String host = "https://ali-weather.showapi.com";
        String path = "/weatherhistory";
        String method = "GET";
        String appcode = "bf3fdb75236649a79044d7748529beba";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<>();
        querys.put("areaCode", "areaCode");
        querys.put("area", "丽江");
        querys.put("month", "201601");
        querys.put("startDate", "20160504");
        querys.put("endDate", "20160510");

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null";
    }
}
