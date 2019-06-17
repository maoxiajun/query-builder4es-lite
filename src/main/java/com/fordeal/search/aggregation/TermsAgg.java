package com.fordeal.search.aggregation;

import com.fordeal.search.base.Constants;
import com.fordeal.search.base.Maps2;
import com.fordeal.search.base.UnThreadSafe;

import java.util.Map;

/**
 * 多值统计，相当于sql的group by，建议手动设置size大小，默认返回top10
 * Created by maoxiajun on 18/1/12.
 */
@UnThreadSafe
public class TermsAgg extends BaseAggs<TermsAgg> {

    private String field;
    private int size = Constants.UNSET;
    private Comparable[] includes;
    private Comparable[] excludes;

    /**
     * 指定统计的结果名称
     * @param aggName 统计结果名称
     */
    public TermsAgg(String aggName) {
        super(aggName);
    }

    /**
     * 指定统计的字段
     * @param field 字段名
     * @return self
     */
    public TermsAgg field(String field) {
        this.field = field;
        return this;
    }

    /**
     * 只统计指定的值范围，每次操作覆盖之前设置的值
     * @param includes 指定值
     * @return self
     */
    public TermsAgg include(Comparable... includes) {
        if (null != includes) {
            this.includes = includes;
        }
        return this;
    }

    /**
     * 排除统计指定的值范围，每次操作覆盖之前设置的值
     * @param excludes 指定值
     * @return self
     */
    public TermsAgg exclude(Comparable... excludes) {
        if (null != excludes) {
            this.excludes = excludes;
        }
        return this;
    }

    /**
     * 指定返回前多少个结果，最多300
     * @param size 返回前多少个结果
     * @return self
     */
    public TermsAgg size(int size) {
        this.size = size;
        return this;
    }

    @Override
    public String cond() {
        return "terms";
    }

    @Override
    public Object value() {
        Map<String, Object> value = Maps2.of("field", this.field);
        if (size != Constants.UNSET) {
            value.put("size", size);
        }
        if (null != this.includes && 0 != this.includes.length) {
            value.put("include", this.includes);
        }
        if (null != this.excludes && 0 != this.excludes.length) {
            value.put("exclude", this.excludes);
        }
        return toBodyWithNested(value);
    }

}
