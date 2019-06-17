package com.youzan.bigdata.aggregation;

import com.fordeal.search.aggregation.Cardinality;
import com.fordeal.search.aggregation.TermsAgg;
import com.fordeal.search.builder.QueryBuilderX5;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by maoxiajun on 2018/10/29.
 */
public class CardinalityTest {

    private Cardinality cardinality;

    @Before
    public void setUp() {
        cardinality = new Cardinality("author_count").field("id_hash");
    }

    @Test
    public void testBuild() {
        assertEquals("{\"author_count\":{\"cardinality\":{\"field\":\"id_hash\"}}}",
                cardinality.toJsonString());
    }

    @Test
    public void testPrecision() {
        cardinality.precision(100);
        assertEquals("{\"author_count\":{\"cardinality\":{\"precision_threshold\":100,\"field\":\"id_hash\"}}}",
                cardinality.toJsonString());
    }

    @Test
    public void testNested() {
        TermsAgg terms = new TermsAgg("notify_count").field("app");
        terms.sub(new Cardinality("alarm_count").field("alarm_record_id"));
        QueryBuilderX5 x5 = new QueryBuilderX5();
        x5.addAggs(terms);
        assertEquals("{\"size\":0,\"query\":{\"match_all\":{}},\"from\":0,\"aggs\":{\"notify_count\":{\"terms\":{\"field\":\"app\"},\"aggs\":{\"alarm_count\":{\"cardinality\":{\"field\":\"alarm_record_id\"}}}}}}",
                x5.toJsonString());
    }

}