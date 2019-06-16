package com.youzan.bigdata.builder;

import com.youzan.bigdata.sort.GeoDistanceSort;

/**
 * 排序条件组装
 * Created by maoxiajun on 18/4/2.
 */
public class SortBuilder {

    public static GeoDistanceSort geoDistanceSort() {
        return new GeoDistanceSort();
    }

}
