package com.fordeal.search.base;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by maoxiajun on 16/5/24.
 */
public interface QueryCondition {

    /**
     * 条件名称，系统调用，外部一般不需使用
     * @return self
     */
    String cond();

    /**
     * 条件值，map形式，系统调用，外部一般不需使用
     * @return self
     */
    Object value();

    /**
     * 返回单条件序列化后的json字符串
     * @return json
     */
    default String toJsonString() {
        return JSON.toJSONString(toMap());
    }

    /**
     * 返回单条件的键值对
     * @return map
     */
    default Map<String, Object> toMap() {
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
        map.put(cond(), value());
        return map;
    }

    // TODO 增加validate，检查不通过抛出异常

}
