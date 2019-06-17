package com.fordeal.search.aggregation;

import com.fordeal.search.base.Maps2;

import java.util.Map;

/**
 * 统计查询，计算指定字段的平均值
 * Created by maoxiajun on 18/1/12.
 */
public class Avg extends BaseAggs<Avg> {

    private String field;
    private Integer missing = null;

    /**
     * 指定统计查询的名称
     * @param name 统计结果的名称
     */
    public Avg(String name) {
        super(name);
    }

    /**
     * 指定统计查询的字段
     * @param field 字段名
     * @return self
     */
    public Avg field(String field) {
        this.field = field;
        return this;
    }

    /**
     * 设置字段值不存在情况下的默认值
     * @param missing 默认值
     * @return self
     */
    public Avg missing(int missing) {
        this.missing = missing;
        return this;
    }

    @Override
    public String cond() {
        return "avg";
    }

    @Override
    public Object value() {
        Map<String, Object> value = Maps2.of("field", this.field);
        if (null != missing) {
            value.put("missing", this.missing);
        }
        return toBodyWithNested(value);
    }

}
