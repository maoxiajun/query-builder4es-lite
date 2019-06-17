package com.youzan.bigdata.query;

import com.fordeal.search.query.Bool;
import com.fordeal.search.query.Range;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by maoxiajun on 17/8/16.
 */
public class RangeTest {

    @Test
    public void testX1() {
        Range range = new Range("foo");
        range.gt(1).lt(5);

        Assert.assertEquals("{\"range\":{\"foo\":{\"lt\":5,\"gt\":1}}}", range.toJsonString());

        range.boost(3);
        Assert.assertEquals("{\"range\":{\"foo\":{\"lt\":5,\"boost\":3.0,\"gt\":1}}}", range.toJsonString());
    }

    @Test
    public void testX5() {
        Range range = new Range("bar");
        range.gte(100);

        Bool bool = new Bool();
        bool.must(range);

        range.lte(200);
        System.out.println(bool.toJsonString());
    }

}
