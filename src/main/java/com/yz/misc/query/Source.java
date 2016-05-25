package com.yz.misc.query;

import java.util.*;

/**
 * Created by maoxiajun on 16/5/24.
 */
public class Source implements QueryCondition {

    private List includes;
    private List excludes;

    public Source() {
        this.includes = new LinkedList<>();
        this.excludes = new LinkedList<>();
    }

    public Source includes(String... includes) {
        this.includes.addAll(Arrays.asList(includes));
        return this;
    }

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
        Map params = new HashMap<>();
        if (!this.includes.isEmpty()) {
            params.put("includes", this.includes);
        }
        if (!this.excludes.isEmpty()) {
            params.put("excludes", this.excludes);
        }
        return params;
    }
}
