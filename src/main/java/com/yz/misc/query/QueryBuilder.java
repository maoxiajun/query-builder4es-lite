package com.yz.misc.query;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by maoxiajun on 16/5/24.
 */
public class QueryBuilder {

    private Map<String, Object> query;
    private Map<String, Object> filter;
    private int size;
    private int from;
    private QueryCondition sort;
    private QueryCondition source;

    public QueryBuilder() {
        this.query = new HashMap<>();
        this.filter = new HashMap<>();
        this.size = 10;
        this.from = 0;
    }

    public QueryBuilder addFilter(QueryCondition cond) {
        this.filter.put(cond.cond(), cond.value());
        return this;
    }

    public QueryBuilder addQuery(QueryCondition cond) {
        this.query.put(cond.cond(), cond.value());
        return this;
    }

    public QueryBuilder size(int size) {
        this.size = size;
        return this;
    }

    public QueryBuilder from(int from) {
        this.from = from;
        return this;
    }

    public QueryBuilder sort(QueryCondition cond) {
        this.sort = cond;
        return this;
    }

    public QueryBuilder source(QueryCondition cond) {
        this.source = cond;
        return this;
    }

    public String toJSONString() {
        Map<String, Object> finalQuery = new HashMap<>();
        if (!this.filter.isEmpty()) {
            Map<String, Object> filtered = new HashMap<>();
            filtered.put("filter", this.filter);
            if (!this.query.isEmpty()) {
                filtered.put("query", this.query);
            }
            Map outQuery = new HashMap<>();
            outQuery.put("filtered", filtered);
            finalQuery.put("query", outQuery);
        } else if (!this.query.isEmpty()) {
            finalQuery.put("query", this.query);
        } else {
            Map<String, Object> matchAll = new HashMap<>();
            matchAll.put("match_all", new HashMap<>());
            finalQuery.put("query", matchAll);
        }
        finalQuery.put("from", this.from);
        finalQuery.put("size", this.size);
        if (null != this.sort) {
            finalQuery.put(this.sort.cond(), this.sort.value());
        }
        if (null != this.source) {
            finalQuery.put(this.source.cond(), this.source.value());
        }
        return JSON.toJSONString(finalQuery);
    }

    @Override
    public String toString() {
        return toJSONString();
    }

    public static void main(String[] args) {
        QueryBuilder builder = new QueryBuilder();
        builder.addFilter(new Bool().must(new Term("kdt_id", 1)));
        builder.addQuery(new Bool().should(new Term("id", 1)).should(new Match("title", "xxx")));
        builder.from(0).size(10);
        builder.sort(new Sort().desc("_id").asc("_doc"));
        builder.source(new Source().includes("id").excludes("title"));
        System.out.println(builder.toJSONString());
    }

}
