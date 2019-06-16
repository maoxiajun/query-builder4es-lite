package com.youzan.bigdata.aggregation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by maoxiajun on 18/1/15.
 */
public class AvgTest {

    private Avg avg;

    @Before
    public void setUp() {
        avg = new Avg("avg_price");
    }

    @Test
    public void testAvg() {
        avg.field("price").missing(10);
        assertEquals("{\"avg_price\":{\"avg\":{\"field\":\"price\",\"missing\":10}}}", avg.toJsonString());
    }

}
