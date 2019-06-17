package com.fordeal.search.query;

import com.fordeal.search.base.Maps2;
import com.fordeal.search.base.QueryCondition;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 相当于sql的where bar in (1, 2, 3)，也是个costly查询，重复两次即会进入缓存
 * <br>
 * Created by maoxiajun on 16/5/24.
 */
public class Terms implements QueryCondition {

    private String field;
    private List<Comparable> values;

    /**
     * 构造器，指定字段名
     * @param field 字段名
     */
    public Terms(String field) {
        this(field, new LinkedList<>());
    }

    /**
     * 构造器，同时指定字段名和值
     * @param field 字段名
     * @param values 多值
     */
    public Terms(String field, List<Comparable> values) {
        this.field = field;
        this.values = values;
    }

    /**
     * 构造器，同时指定字段名和值
     * @param field 字段名
     * @param values 可变多值
     */
    public Terms(String field, Comparable... values) {
        this.field = field;
        this.values = Arrays.asList(values);
    }

    /**
     * 添加一个多值条件，不会覆盖
     * @param value 设定值
     * @return self
     */
    public Terms value(Comparable value) {
        this.values.add(value);
        return this;
    }

    /**
     * 添加多个多值条件，不会覆盖
     * @param values 设定的多个值
     * @return self
     */
    public Terms values(Comparable... values) {
        this.values.addAll(Arrays.asList(values));
        return this;
    }

    @Override
    public String cond() {
        return "terms";
    }

    @Override
    public Object value() {
        Map<String, Object> params = Maps2.of(1);
        params.put(field, values);
        return params;
    }

}
