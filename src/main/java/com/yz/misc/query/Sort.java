package com.yz.misc.query;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by maoxiajun on 16/5/24.
 */
public class Sort implements QueryCondition {

    private List sort;

    public Sort() {
        this.sort = new LinkedList<>();
    }

    public Sort desc(String field) {
        Map desc = new HashMap<>();
        desc.put(field, "desc");
        sort.add(desc);
        return this;
    }

    public Sort asc(String field) {
        Map asc = new HashMap<>();
        asc.put(field, "asc");
        sort.add(asc);
        return this;
    }

    @Override
    public String cond() {
        return "sort";
    }

    @Override
    public Object value() {
        return sort;
    }
}
