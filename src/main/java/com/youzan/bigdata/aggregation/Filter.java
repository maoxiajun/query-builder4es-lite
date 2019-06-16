package com.youzan.bigdata.aggregation;

import com.youzan.bigdata.query.Term;

import java.util.Map;

/**
 * 统计查询，统计符合某个条件的记录条数
 * Created by maoxiajun on 18/1/12.
 */
public class Filter extends BaseAggs<Filter> {

    private Term term;

    /**
     * 指定统计结果名称
     * @param name 结果名称
     */
    public Filter(String name) {
        super(name);
    }

    /**
     * 指定某个条件，返回符合条件的记录条数
     * @param term 过滤条件
     * @return self
     */
    public Filter term(Term term) {
        this.term = term;
        return this;
    }

    @Override
    public String cond() {
        return "filter";
    }

    @Override
    public Object value() {
        Map<String, Object> value = term.toMap();
        return toBodyWithNested(value);
    }

}
