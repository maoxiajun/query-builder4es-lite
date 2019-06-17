package com.fordeal.search.base;

/**
 * Created by maoxiajun on 18/2/1.
 */
public interface Boostable<T> {

    /**
     * 设置查询条件的提权
     * @param boost 提权大小
     * @return T
     */
    T boost(double boost);

}
