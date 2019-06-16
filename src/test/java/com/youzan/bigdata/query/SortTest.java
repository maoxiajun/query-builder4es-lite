package com.youzan.bigdata.query;

import com.youzan.bigdata.QueryBuilders;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by maoxiajun on 17/8/16.
 */
public class SortTest {

    @Test
    public void testX1() {
        Sort sort = new Sort().asc("foo").desc("bar");
        Assert.assertEquals("{\"sort\":[{\"foo\":\"asc\"},{\"bar\":\"desc\"}]}", sort.toJsonString());
    }

}
