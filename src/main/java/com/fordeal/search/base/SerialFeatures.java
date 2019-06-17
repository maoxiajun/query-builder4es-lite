package com.fordeal.search.base;

/**
 * json序列化配置
 * Created by maoxiajun on 17/11/21.
 */
public enum SerialFeatures {

    /**
     * 按照fastjson默认配置序列化，日期变为时间戳
     */
    SAME_AS_FASTJSON_DEFAULT,

    /**
     * 指定日期格式，yyyy-MM-dd HH:mm:ss
     */
    WRITE_DATE_WITH_DATEFORMT

}
