package com.youzan.bigdata.aggregation;

import com.youzan.bigdata.base.Constants;
import com.youzan.bigdata.base.Maps2;
import com.youzan.bigdata.base.QueryException;
import com.youzan.bigdata.base.UnThreadSafe;

import java.util.Map;

/**
 * distinct统计
 * Created by maoxiajun on 2018/10/29.
 */
@UnThreadSafe
public class Cardinality extends BaseAggs<Cardinality> {

    private String field;
    private int precisionThreshold = Constants.DEFAULT_PRECISION;

    /**
     * 统计名称
     * @param name 统计查询名
     */
    public Cardinality(String name) {
        super(name);
    }

    /**
     * 指定统计的字段
     * @param field 字段名
     * @return self
     */
    public Cardinality field(String field) {
        this.field = field;
        return this;
    }

    /**
     * 指定要统计的精确程度，比如该字段包含几亿的不重复值，那么这个统计会有一定失真，这里设置越大越精确，上限40000，默认3000
     * @param precisionThreshold 精确程度
     * @return self
     */
    public Cardinality precision(int precisionThreshold) {
        if (precisionThreshold > Constants.MAX_PRECISION || precisionThreshold < 0) {
            throw new QueryException("invalid precision threshold, must be in [0, 40000]");
        }
        this.precisionThreshold = precisionThreshold;
        return this;
    }

    @Override
    public String cond() {
        return "cardinality";
    }

    @Override
    public Object value() {
        Map<String, Object> value = Maps2.of("field", this.field);
        if (this.precisionThreshold != Constants.DEFAULT_PRECISION) {
            value.put("precision_threshold", this.precisionThreshold);
        }
        return toBodyWithNested(value);
    }

}
