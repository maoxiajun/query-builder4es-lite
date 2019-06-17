package com.youzan.bigdata.query;

import com.fordeal.search.QueryBuilders;
import com.fordeal.search.query.Bool;
import com.fordeal.search.query.Term;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by maoxiajun on 17/8/16.
 */
public class TermTest {

    private Term term, term2;

    @Before
    public void setUp() {
        term = new Term("foo", 1);
        term2 = new Term("bar", "134");
    }

    @Test
    public void testX1() {
        Assert.assertEquals("{\"term\":{\"foo\":1}}", term.toJsonString());
    }

    @Test
    public void testBuilder() {
        Assert.assertEquals("{\"size\":10,\"query\":{\"bool\":{\"must\":[{\"term\":{\"foo\":1}},{\"term\":{\"bar\":\"134\"}}]}},\"from\":0}",
                QueryBuilders.x1().addQuery(new Bool().must(term).must(term2)).toJsonString());
    }

}
