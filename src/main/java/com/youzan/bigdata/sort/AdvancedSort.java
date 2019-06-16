package com.youzan.bigdata.sort;

import com.youzan.bigdata.base.BaseSort;

import java.util.Map;

/**
 * 高级排序设置
 * Created by maoxiajun on 2018/6/12.
 */
public class AdvancedSort extends BaseSort<AdvancedSort> {

    private Comparable missing = null;
    private Mode mode = null;

    /**
     * 设置当字段不存在时用来参与排序的默认值
     * @param missing 默认值
     * @return self
     */
    public AdvancedSort missing(Comparable missing) {
        this.missing = missing;
        return this;
    }

    /**
     * 设置排序模式，一般不用设置
     * @param mode 排序模式
     * @return self
     */
    public AdvancedSort mode(Mode mode) {
        this.mode = mode;
        return this;
    }

    @Override
    public Map<String, Object> output() {
        Map<String, Object> output = super.output();
        if (null != this.missing) {
            output.put("missing", missing);
        }
        if (null != this.mode) {
            output.put("mode", mode.toString());
        }
        return output;
    }

}
