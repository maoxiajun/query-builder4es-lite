package com.yz.misc.query;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maoxiajun on 16/5/24.
 */
public class Term implements QueryCondition {
    public String field;
    public Object value;

    public Term(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public String cond() {
        return "term";
    }

    @Override
    public Object value() {
        Map params = new HashMap<>();
        params.put(field, value);
        return params;
    }
}
