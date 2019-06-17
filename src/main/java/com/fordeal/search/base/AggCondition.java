package com.fordeal.search.base;

import java.util.Map;

/**
 * Created by maoxiajun on 18/1/12.
 */
public interface AggCondition<T> extends QueryCondition {

    /**
     * 返回统计查询聚合后返回的名称
     * @return 聚合名称
     */
    String name();

    /**
     * 嵌套统计
     * @param agg 嵌套agg
     * @return 当前操作的agg
     */
    T sub(AggCondition agg);

    /**
     * 返回单条件的键值对
     * @return map
     */
    @Override
    default Map<String, Object> toMap() {
        return Maps2.of(name(), value());
    }

}
