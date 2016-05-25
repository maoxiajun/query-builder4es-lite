package com.yz.misc.query;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maoxiajun on 16/5/24.
 */
public class Range implements QueryCondition {

    private String field;
    private Number from;
    private Number to;
    private boolean includeUpper;
    private boolean includeLower;

    public Range(String field) {
        this.field = field;
    }

    public Range field(String field) {
        this.field = field;
        return this;
    }

    public Range gt(Number gt) {
        this.from = gt;
        this.includeLower = false;
        return this;
    }

    public Range gte(Number gte) {
        this.from = gte;
        this.includeLower = true;
        return this;
    }

    public Range lt(Number lt) {
        this.to = lt;
        this.includeUpper = false;
        return this;
    }

    public Range lte(Number lte) {
        this.to = lte;
        this.includeUpper = true;
        return this;
    }

    @Override
    public String cond() {
        return "range";
    }

    @Override
    public Object value() {
        Map inner = new HashMap<>();
        if (this.from != null) {
            if (includeLower) {
                inner.put("gte", from);
            } else {
                inner.put("gt", from);
            }
        }
        if (this.to != null) {
            if (includeUpper) {
                inner.put("lte", to);
            } else {
                inner.put("lt", to);
            }
        }
        Map params = new HashMap<>();
        params.put(field, inner);
        return params;
    }
}
