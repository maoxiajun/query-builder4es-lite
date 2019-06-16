package com.youzan.bigdata.query;

import com.youzan.bigdata.QueryBuilders;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by maoxiajun on 17/8/16.
 */
public class BoolTest {

    @Test
    public void testES1() {
        Bool bool = new Bool()
                .must(new Term("foo", 1))
                .must(new Terms("bar", 1, 2, 3))
                .should(new Term("abc", "11"))
                .should(new Terms("code", 11, "134"))
                .minimumShouldMatch(1);

        Assert.assertEquals("{\"bool\":{\"should\":[{\"term\":{\"abc\":\"11\"}},{\"terms\":{\"code\":[11,\"134\"]}}],\"minimum_should_match\":1,\"must\":[{\"term\":{\"foo\":1}},{\"terms\":{\"bar\":[1,2,3]}}]}}",
                bool.toJsonString());
    }

    @Test
    public void testES5() {
        Bool bool = new Bool()
                .must(new Term("foo", 1))
                .must(new Terms("bar", 1, 2, 3))
                .should(new Term("abc", "11"))
                .should(new Terms("code", 11, "123"))
                .filter(new Term("type", 123))
                .minimumShouldMatch(1);

        Assert.assertEquals("{\"bool\":{\"should\":[{\"term\":{\"abc\":\"11\"}},{\"terms\":{\"code\":[11,\"123\"]}}],\"filter\":[{\"term\":{\"type\":123}}],\"minimum_should_match\":1,\"must\":[{\"term\":{\"foo\":1}},{\"terms\":{\"bar\":[1,2,3]}}]}}",
                bool.toJsonString());
    }

}
