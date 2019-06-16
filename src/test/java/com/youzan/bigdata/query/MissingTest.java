package com.youzan.bigdata.query;

import com.youzan.bigdata.QueryBuilders;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by maoxiajun on 17/8/16.
 */
public class MissingTest {

    @Test
    public void testX1() {
        Missing missing = new Missing("foo");
        Assert.assertEquals("{\"missing\":{\"field\":\"foo\"}}", missing.toJsonString());
    }

}
