package com.fordeal.search.base;

import java.util.Map;

/**
 * 排序条件, 高级使用方式
 * Created by maoxiajun on 18/4/2.
 */
public class BaseSort<T extends BaseSort> {

    /** 排序模式 */
    public enum Mode {
        MIN("min"), MAX("max"), MEDIAN("median"), AVG("avg"), SUM("sum");

        private String mode;
        Mode(String mode) {
            this.mode = mode;
        }

        @Override
        public String toString() {
            return this.mode;
        }
    }

    /**
     * 排序方式
     */
    public enum OrderType {
        ASC("asc"), DESC("desc");

        private String order;
        OrderType(String order) {
            this.order = order;
        }

        @Override
        public String toString() {
            return this.order;
        }
    }

    private String name;
    private OrderType order = OrderType.DESC;

    /**
     * 返回字段名
     * @return 字段名
     */
    public String name() {
        return this.name;
    }

    /**
     * 设置字段名
     * @param name 字段名
     * @return self
     */
    public T name(String name) {
        this.name = name;
        return (T) this;
    }

    /**
     * 设置升序／降序
     * @param order 升序／降序
     * @return self
     */
    public T order(OrderType order) {
        this.order = order;
        return (T) this;
    }

    /**
     * 返回排序顺序
     * @return 升序／降序
     */
    public String order() {
        return this.order.toString();
    }

    /**
     * 排序条件详细内容
     * @return 详细配置
     */
    public Map<String, Object> output() {
        Map<String, Object> output = Maps2.of();
        output.put("order", order());
        return output;
    }

}
