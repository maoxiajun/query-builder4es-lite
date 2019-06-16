package com.youzan.bigdata.aggregation;

import com.youzan.bigdata.QueryBuilders;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by maoxiajun on 18/1/15.
 */
public class MaxTest {

    private Max max;

    @Before
    public void setUp() {
        max = new Max("max_price");
        max.field("price");
    }

    @Test
    public void testBuild() {
        assertEquals("{\"max_price\":{\"max\":{\"field\":\"price\"}}}",
                max.toJsonString());
    }

    @Test
    public void testBuilder() {
        assertEquals("{\"size\":0,\"query\":{\"match_all\":{}},\"from\":0,\"aggs\":{\"max_price\":{\"max\":{\"field\":\"price\"}}}}",
                QueryBuilders.x5().addAggs(max).toJsonString());
    }

}