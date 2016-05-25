package com.yz.misc.query;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maoxiajun on 16/5/24.
 */
public class Match implements QueryCondition {

    private String field;
    private Object value;

    public Match(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public String cond() {
        return "match";
    }

    @Override
    public Object value() {
        Map params = new HashMap<>();
        params.put(field, value);
        return params;
    }
}
