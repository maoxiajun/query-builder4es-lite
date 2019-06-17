package com.youzan.bigdata.query;

import com.fordeal.search.query.Exists;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by maoxiajun on 17/8/16.
 */
public class ExistsTest {

    @Test
    public void test() {
        Exists exists = new Exists("foo");
        Assert.assertEquals("{\"exists\":{\"field\":\"foo\"}}", exists.toJsonString());
    }

}
