package com.fordeal.search.aggregation;

import com.fordeal.search.base.Maps2;

import java.util.Map;

/**
 * 统计某个字段的最大值
 * Created by maoxiajun on 18/1/12.
 */
public class Max extends BaseAggs<Max> {

    private String field;

    /**
     * 指定统计结果名称
     * @param name 结果名称
     */
    public Max(String name) {
        super(name);
    }

    /**
     * 指定需要统计的字段
     * @param field 字段名
     * @return self
     */
    public Max field(String field) {
        this.field = field;
        return this;
    }

    @Override
    public String cond() {
        return "max";
    }

    @Override
    public Object value() {
        Map<String, Object> value = Maps2.of("field", this.field);
        return toBodyWithNested(value);
    }
}
