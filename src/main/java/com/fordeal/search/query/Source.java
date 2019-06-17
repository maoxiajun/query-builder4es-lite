package com.fordeal.search.query;

import com.google.common.collect.Maps;
import com.fordeal.search.base.QueryCondition;

import java.util.*;

/**
 * 过滤返回字段，相当于 select foo, bar from table
 * <br>
 * Created by maoxiajun on 16/5/24.
 */
public class Source implements QueryCondition {

    private List<String> includes;
    private List<String> excludes;

    public Source() {
        this.includes = new LinkedList<>();
        this.excludes = new LinkedList<>();
    }

    /**
     * 指定需要返回的字段
     * @param includes 字段名
     * @return self
     */
    public Source includes(String... includes) {
        this.includes.addAll(Arrays.asList(includes));
        return this;
    }

    /**
     * 指定不需要返回的字段，如果指定了{@link #includes(String...)}，这里就不用指定了
     * @param excludes 字段名
     * @return self
     */
    public Source excludes(String... excludes) {
        this.excludes.addAll(Arrays.asList(excludes));
        return this;
    }

    @Override
    public String cond() {
        return "_source";
    }

    @Override
    public Object value() {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        if (!this.includes.isEmpty()) {
            params.put("includes", this.includes);
        }
        if (!this.excludes.isEmpty()) {
            params.put("excludes", this.excludes);
        }
        return params;
    }
}