package com.fordeal.search.builder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fordeal.search.base.*;

import java.util.Collections;
import java.util.Map;

/**
 * 适配es5.x版本
 * Created by maoxiajun on 17/8/9.
 */
@UnThreadSafe
public class QueryBuilderX5 {

    private final Map<String, Object> query;
    private Map<String, Object> aggs = null;
    private int size;
    private int from;
    private QueryCondition sort;
    private QueryCondition source;
    private QueryCondition highlight;

    public QueryBuilderX5() {
        this.query = Maps2.of();
        this.size = Constants.UNSET;
        this.from = 0;
    }

    /**
     * 添加查询条件
     * @param cond 查询条件
     * @return self
     */
    public QueryBuilderX5 addQuery(QueryCondition cond) {
        this.query.put(cond.cond(), cond.value());
        return this;
    }

    /**
     * 添加统计查询
     * @param cond 统计查询
     * @return 当前操作的builder
     */
    public QueryBuilderX5 addAggs(AggCondition cond) {
        if (null == this.aggs) {
            this.aggs = Maps2.of();
        }
        this.aggs.put(cond.name(), cond.value());
        return this;
    }

    /**
     * 设置查询需要返回的记录条数
     * @param size 需要返回的记录条数
     * @return self
     */
    public QueryBuilderX5 size(int size) {
        this.size = size;
        return this;
    }

    /**
     * 设置查询结果的偏移量
     * @param from 从from条记录后开始返回结果
     * @return self
     */
    public QueryBuilderX5 from(int from) {
        this.from = from;
        return this;
    }

    /**
     * 设置排序条件，设置后会覆盖已有条件
     * @param cond 排序条件
     * @return self
     */
    public QueryBuilderX5 sort(QueryCondition cond) {
        this.sort = cond;
        return this;
    }

    /**
     * 设置返回字段，设置后会覆盖已有设置
     * @param cond 字段条件
     * @return self
     */
    public QueryBuilderX5 source(QueryCondition cond) {
        this.source = cond;
        return this;
    }

    /**
     * 设置高亮查询条件
     * @param cond 高亮查询
     * @return self
     */
    public QueryBuilderX5 highlight(QueryCondition cond) {
        this.highlight = cond;
        return this;
    }

    @Override
    public String toString() {
        return toJsonString();
    }

    /**
     * 返回json格式字符串
     * @return json格式字符串
     */
    public String toJsonString() {
        return toJsonString(SerialFeatures.WRITE_DATE_WITH_DATEFORMT);
    }

    /**
     * 按照指定的序列化设置返回json格式字符串
     * @param features 序列化设置, 参考 {@link SerialFeatures}
     * @return self
     */
    public String toJsonString(SerialFeatures features) {
        Map<String, Object> finalQuery = Maps2.of();

        // query & filter
        if (!this.query.isEmpty()) {
            finalQuery.put("query", this.query);
        } else {
            Map<String, Object> matchAll = Maps2.of();
            matchAll.put("match_all", Collections.emptyMap());
            finalQuery.put("query", matchAll);
        }

        // sort & source & highlight
        if (null != this.sort) {
            finalQuery.put(this.sort.cond(), this.sort.value());
        }
        if (null != this.source) {
            finalQuery.put(this.source.cond(), this.source.value());
        }
        if (null != this.highlight) {
            finalQuery.put(this.highlight.cond(), this.highlight.value());
        }

        // aggregations
        if (null != this.aggs) {
            finalQuery.put("aggs", this.aggs);
        }

        // from & size
        finalQuery.put("from", this.from);
        if (null != this.aggs) {
            // 没有查询条件的统计，默认size=0
            finalQuery.put("size", Constants.UNSET == this.size ? 0 : this.size);
        } else {
            // 其余默认size=10
            finalQuery.put("size", Constants.UNSET == this.size ? 10 : this.size);
        }

        switch (features) {
            case WRITE_DATE_WITH_DATEFORMT:
                return JSON.toJSONString(finalQuery, SerializerFeature.WriteDateUseDateFormat);
            case SAME_AS_FASTJSON_DEFAULT:
            default:
                return JSON.toJSONString(finalQuery);
        }
    }

}
