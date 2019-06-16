package com.youzan.bigdata;

import com.youzan.bigdata.builder.QueryBuilderX1;
import com.youzan.bigdata.builder.QueryBuilderX5;

/**
 * Created by maoxiajun on 17/8/9.
 */
public class QueryBuilders {

    public static QueryBuilderX1 x1() {
        return new QueryBuilderX1();
    }

    public static QueryBuilderX5 x5() {
        return new QueryBuilderX5();
    }

}
