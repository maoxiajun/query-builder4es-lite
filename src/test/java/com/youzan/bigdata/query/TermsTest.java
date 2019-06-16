package com.youzan.bigdata.query;

import com.youzan.bigdata.QueryBuilders;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
