package com.youzan.bigdata.aggregation;

import com.fordeal.search.QueryBuilders;
import com.fordeal.search.aggregation.Min;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by maoxiajun on 18/1/15.
 */
public class MinTest {

    private Min min;

    @Before
    public void setUp() {
        min = new Min("min_price");
        min.field("price");
    }

    @Test
    public void testBuild() {
        assertEquals("{\"min_price\":{\"min\":{\"field\":\"price\"}}}",
                min.toJsonString());
    }

    @Test
    public void testMissing() {
        min.missing(10);
        assertEquals("{\"min_price\":{\"min\":{\"field\":\"price\",\"missing\":10}}}",
                min.toJsonString());
    }

    @Test
    public void testBuilder() {
        assertEquals("{\"size\":0,\"query\":{\"match_all\":{}},\"from\":0,\"aggs\":{\"min_price\":{\"min\":{\"field\":\"price\"}}}}",
                QueryBuilders.x5().addAggs(min).toJsonString());
    }

}
