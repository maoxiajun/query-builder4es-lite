package com.fordeal.search.query;

import com.fordeal.search.base.Maps2;
import com.fordeal.search.base.QueryCondition;

/**
 * 前缀查询
 * <br>
 * Created by maoxiajun on 16/5/24.
 */
@Deprecated
public class Prefix implements QueryCondition {
    public String field;
    public Comparable value;

    /**
     * 前缀查询，不推荐用
     * @param field 字段名
     * @param value 查询值
     */
    public Prefix(String field, Comparable value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public String cond() {
        return "prefix";
    }

    @Override
    public Object value() {
        return Maps2.of(field, value);
    }
}