package com.youzan.bigdata.aggregation;

import com.youzan.bigdata.QueryBuilders;
import com.youzan.bigdata.query.Bool;
import com.youzan.bigdata.query.Range;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by maoxiajun on 18/1/15.
 */
public class SumTest {

    private Sum sum, sum2;

    @Before
    public void setUp() {
        sum = new Sum("intraday_return").field("change");
        sum2 = new Sum("total_income").field("income");
    }

    @Test
    public void testBuild() {
        assertEquals("{\"intraday_return\":{\"sum\":{\"field\":\"change\"}}}",
                sum.toString());
    }

    @Test
    public void testMissing() {
        sum.missing(10);
        assertEquals("{\"intraday_return\":{\"sum\":{\"field\":\"change\",\"missing\":10}}}",
                sum.toString());
    }

    @Test
    public void testBuilder() {
        assertEquals("{\"size\":0,\"query\":{\"bool\":{\"filter\":[{\"range\":{\"time\":{\"lte\":10000,\"gt\":100}}}]}},\"from\":0,\"aggs\":{\"intraday_return\":{\"sum\":{\"field\":\"change\"}}}}",
                QueryBuilders.x5().addQuery(new Bool().filter(new Range("time").gt(100).lte(10000))).addAggs(sum).toJsonString());
    }

    @Test
    public void testMultiAgg() {
        assertEquals("{\"size\":0,\"query\":{\"match_all\":{}},\"from\":0,\"aggs\":{\"total_income\":{\"sum\":{\"field\":\"income\"}},\"intraday_return\":{\"sum\":{\"field\":\"change\"}}}}",
                QueryBuilders.x5().addAggs(sum).addAggs(sum2).toJsonString());
    }

}