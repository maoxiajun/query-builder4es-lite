package com.fordeal.search.query;

import com.google.common.collect.Maps;
import com.fordeal.search.base.QueryCondition;

import java.util.Map;

/**
 * es 5x版本已经取消了missing查询，请查看{@link Exists}
 */
@Deprecated
public class Missing implements QueryCondition {

    public String field;

    /**
     * 构造器，指定不存在的字段
     * @param field 字段名
     */
    public Missing(String field) {
        super();
        this.field = field;
    }

    @Override
    public String cond() {
        return "missing";
    }

    @Override
    public Object value() {
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
        map.put("field", field);
        return map;
    }

}
