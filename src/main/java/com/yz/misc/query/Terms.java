package com.yz.misc.query;

import java.util.*;

/**
 * Created by maoxiajun on 16/5/24.
 */
public class Terms implements QueryCondition {

    private String field;
    private List values;

    public Terms(String field) {
        this(field, new LinkedList<>());
    }

    public Terms(String field, List values) {
        this.field = field;
        this.values = values;
    }

    public Terms value(Object value) {
        this.values.add(value);
        return this;
    }

    public Terms values(Object... values) {
        this.values.addAll(Arrays.asList(values));
        return this;
    }

    @Override
    public String cond() {
        return "terms";
    }

    @Override
    public Object value() {
        Map params = new HashMap<>();
        params.put(field, values);
        return values;
    }
}
