package com.fordeal.search.query;

import com.fordeal.search.base.Maps2;
import com.fordeal.search.base.QueryCondition;

import java.util.Collections;
import java.util.Map;

/**
 * 高亮字段
 * Created by maoxiajun on 17/8/16.
 */
public class HighLightField implements QueryCondition {

    private String field;
    private QueryCondition highlightQuery;

    /**
     * 构造器，指定需要高亮的字段
     * @param field 字段名
     */
    public HighLightField(String field) {
        this.field = field;
    }

    /**
     * 如果用term=1查出来结果，但是想用title：abc来高亮，那么这里就得设置highlight_query
     * @param query 高亮查询
     * @return self
     */
    public HighLightField highLightQuery(QueryCondition query) {
        this.highlightQuery = query;
        return this;
    }

    @Override
    public String cond() {
        return this.field;
    }

    @Override
    public Object value() {
        if (null != highlightQuery) {
            Map<String, Object> fd = Maps2.of(1);
            Map<String, Object> query = Maps2.of(highlightQuery.cond(), highlightQuery.value());
            fd.put("highlight_query", query);
            return fd;
        } else {
            return Collections.emptyMap();
        }
    }
}
