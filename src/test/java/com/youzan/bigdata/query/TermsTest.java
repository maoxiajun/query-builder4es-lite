package com.youzan.bigdata.query;

import com.fordeal.search.QueryBuilders;
import com.fordeal.search.query.Bool;
import com.fordeal.search.query.Terms;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by maoxiajun on 17/8/16.
 */
public class TermsTest {

    private Terms terms, terms2;

    @Before
    public void setUp() {
        terms = new Terms("foo", 1, 2, 3);
        terms2 = new Terms("bar", "1", "2", "3");
    }

    @Test
    public void testX1() {
        Assert.assertEquals("{\"terms\":{\"foo\":[1,2,3]}}", terms.toJsonString());
    }

    @Test
    public void testBuiler() {
        Assert.assertEquals("{\"size\":10,\"query\":{\"bool\":{\"must_not\":[{\"terms\":{\"bar\":[\"1\",\"2\",\"3\"]}}],\"must\":[{\"terms\":{\"foo\":[1,2,3]}}]}},\"from\":0}",
                QueryBuilders.x1().addQuery(new Bool().must(terms).mustNot(terms2)).toJsonString());
    }

}
