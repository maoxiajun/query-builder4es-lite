package com.yz.misc.query;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by maoxiajun on 16/5/24.
 * 非线程安全,注意使用
 */
public class Bool implements QueryCondition {

    private String field;
    private List must;
    private List should;
    private List mustNot;

    public Bool() {
        this.field = "bool";
        this.must = new LinkedList<>();
        this.should = new LinkedList<>();
        this.mustNot = new LinkedList<>();
    }

    public Bool must(QueryCondition cond) {
        Map query = new HashMap<>();
        query.put(cond.cond(), cond.value());
        this.must.add(query);
        return this;
    }

    public Bool should(QueryCondition cond) {
        Map query = new HashMap<>();
        query.put(cond.cond(), cond.value());
        this.should.add(query);
        return this;
    }

    public Bool mustNot(QueryCondition cond) {
        Map query = new HashMap<>();
        query.put(cond.cond(), cond.value());
        this.mustNot.add(query);
        return this;
    }

    @Override
    public String cond() {
        return field;
    }

    @Override
    public Object value() {
        Map params = new HashMap<>();
        if (!this.must.isEmpty()) {
            params.put("must", this.must);
        }
        if (!this.mustNot.isEmpty()) {
            params.put("must_not", this.mustNot);
        }
        if (!this.should.isEmpty()) {
            params.put("should", this.should);
        }
        return params;
    }
}
