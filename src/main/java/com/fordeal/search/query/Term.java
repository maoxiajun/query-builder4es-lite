package com.fordeal.search.query;

import com.fordeal.search.base.Maps2;
import com.fordeal.search.base.QueryCondition;

import java.util.Map;

/**
 * 相当于sql的where foo=1
 * <br>
 * Created by maoxiajun on 16/5/24.
 */
public class Term implements QueryCondition {

    public String field;
    public Comparable value;

    /**
     * 构造器，设定精确匹配条件
     * @param field 字段名
     * @param value 匹配值
     */
    public Term(String field, Comparable value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public String cond() {
        return "term";
    }

    @Override
    public Object value() {
        return Maps2.of(field, value);
    }
}