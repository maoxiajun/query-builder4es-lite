package com.youzan.bigdata.query;

import com.youzan.bigdata.base.Maps2;
import com.youzan.bigdata.base.QueryCondition;
import com.youzan.bigdata.base.BaseSort;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 排序条件，按照调用顺序排序
 * <br>
 * Created by maoxiajun on 16/5/24.
 */
public class Sort implements QueryCondition {

    private List<Object> sort;

    public Sort() {
        this.sort = new LinkedList<>();
    }

    /**
     * 按指定字段倒序排序，简化方式
     * @param field 字段名
     * @return self
     */
    public Sort desc(String field) {
        Map<String, Object> desc = Maps2.of(1);
        desc.put(field, "desc");
        sort.add(desc);
        return this;
    }

    /**
     * 按指定字段生序排序，简化方式
     * @param field 字段名
     * @return self
     */
    public Sort asc(String field) {
        Map<String, Object> asc = Maps2.of(1);
        asc.put(field, "asc");
        sort.add(asc);
        return this;
    }

    /**
     * 使用高级排序条件
     * @param field 排序条件
     * @return self
     */
    public Sort field(BaseSort field) {
        Map<String, Object> so = Maps2.of(1);
        so.put(field.name(), field.output());
        sort.add(so);
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
