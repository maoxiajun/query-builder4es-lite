package com.fordeal.search.aggregation;

import com.fordeal.search.base.AggCondition;
import com.fordeal.search.base.Maps2;
import com.fordeal.search.base.UnThreadSafe;

import java.util.Map;

/**
 * Created by maoxiajun on 18/1/15.
 */
@UnThreadSafe
public abstract class BaseAggs<T> implements AggCondition<T> {

    protected String aggName;
    protected Map<String, Object> nested;

    public BaseAggs(String name) {
        this.aggName = name;
    }

    @Override
    public String name() {
        return this.aggName;
    }

    @Override
    public T sub(AggCondition agg) {
        if (null == this.nested) {
            this.nested = Maps2.of();
        }
        this.nested.put(agg.name(), agg.value());
        return (T) this;
    }

    /**
     * 帮助方法，填充嵌套agg，考虑是否用组合方式，一般外部不需使用
     * @param value agg内容
     * @return 完整的agg查询，map形式
     */
    protected Map<String, Object> toBodyWithNested(Map<String, Object> value) {
        Map<String, Object> body = Maps2.of(cond(), value);
        if (null != this.nested) {
            body.put("aggs", this.nested);
        }
        return body;
    }

    @Override
    public String toString() {
        return toJsonString();
    }
}
