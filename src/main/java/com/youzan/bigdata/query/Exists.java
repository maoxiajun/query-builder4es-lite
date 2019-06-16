package com.youzan.bigdata.query;

import com.google.common.collect.Maps;
import com.youzan.bigdata.base.QueryCondition;

import java.util.Map;

/**
 * 使用方法：
 * "exists": {"field": "user"}，
 * es5取消了missing，替代方法：
 * "bool": {"must_not": {"exists": {"field": "user"}}}
 */
public class Exists implements QueryCondition {

    public String field;

    /**
     * 指定字段必须存在
     * @param field 字段名
     */
    public Exists(String field) {
        super();
        this.field = field;
    }

    @Override
    public String cond() {
        return "exists";
    }

    @Override
    public Object value() {
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
        map.put("field", field);
        return map;
    }

}
