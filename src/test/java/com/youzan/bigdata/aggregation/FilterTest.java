package com.youzan.bigdata.aggregation;

import com.youzan.bigdata.query.Term;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by maoxiajun on 18/1/15.
 */
public class FilterTest {

    private Filter filter;

    @Before
    public void setUp() {
        filter = new Filter("red_products");
        filter.term(new Term("color", "red"));
    }

    @Test
    public void testBuild() {
        assertEquals("{\"red_products\":{\"filter\":{\"term\":{\"color\":\"red\"}}}}", filter.toJsonString());
    }

    @Test
    public void testNested() {
        filter.sub(new Avg("avg_price").field("price"));
        assertEquals("{\"red_products\":{\"filter\":{\"term\":{\"color\":\"red\"}},\"aggs\":{\"avg_price\":{\"avg\":{\"field\":\"price\"}}}}}",
                filter.toJsonString());
    }

}