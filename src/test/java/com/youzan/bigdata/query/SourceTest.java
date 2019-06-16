package com.youzan.bigdata.query;

import com.youzan.bigdata.QueryBuilders;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by maoxiajun on 17/8/16.
 */
public class SourceTest {

    @Test
    public void testX1() {
        Source source = new Source().includes("foo", "bar");
        Assert.assertEquals("{\"_source\":{\"includes\":[\"foo\",\"bar\"]}}", source.toJsonString());
    }

}
