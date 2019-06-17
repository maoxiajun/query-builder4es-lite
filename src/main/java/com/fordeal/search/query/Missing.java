package com.fordeal.search.query;

import com.fordeal.search.base.Maps2;
import com.fordeal.search.base.QueryCondition;

/**
 * es 5x版本已经取消了missing查询，请查看{@link Exists}
 */
@Deprecated
public class Missing implements QueryCondition {

    public String field;

    /**
     * 构造器，指定不存在的字段
     * @param field 字段名
     */
    public Missing(String field) {
        super();
        this.field = field;
    }

    @Override
    public String cond() {
        return "missing";
    }

    @Override
    public Object value() {
        return Maps2.of("field", field);
    }

}
