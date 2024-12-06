package com.zhang.pojo.vo;

import lombok.Data;

/**
 * 当前端传来的json数据，没有对应的实体类接收时，需要自定义一个实体类，用来接收
 */
@Data
public class PortalVo {

    private String keyWords;
    private int type = 0;
    private int pageNum = 1;
    private int pageSize = 10;

}
