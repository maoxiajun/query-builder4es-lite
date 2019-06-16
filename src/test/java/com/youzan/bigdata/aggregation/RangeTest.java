package com.youzan.bigdata.aggregation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by maoxiajun on 18/1/15.
 */
public class RangeTest {

    private Range range;

    @Before
    public void setUp() {
        range = new Range("price_ranges");
        range.field("price");
        range.to(50).interval(50, 100).from(100);
    }

    @Test
    public void testBuild() {
        assertEquals("{\"price_ranges\":{\"range\":{\"field\":\"price\",\"ranges\":[{\"to\":50},{\"from\":50,\"to\":100},{\"from\":100}]}}}",
                range.toJsonString());
    }

    @Test
    public void testSub() {
        range.sub(new Avg("price_avg").field("price"));
        assertEquals("{\"price_ranges\":{\"range\":{\"field\":\"price\",\"ranges\":[{\"to\":50},{\"from\":50,\"to\":100},{\"from\":100}]},\"aggs\":{\"price_avg\":{\"avg\":{\"field\":\"price\"}}}}}",
                range.toJsonString());
    }

}
