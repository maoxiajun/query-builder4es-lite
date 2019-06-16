package com.youzan.bigdata.query;

import com.youzan.bigdata.QueryBuilders;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by maoxiajun on 17/12/12.
 */
public class NestedQueryTest {

    private Nested nested;
    private Bool bool;

    @Before
    public void setUp() {
        nested = new Nested("admin", new Term("admin.admin_id", 5161096));
        bool = new Bool().must(new Term("admin.admin_id", 5161096));
    }

    @Test
    public void testX5() {
        Assert.assertEquals("{\"nested\":{\"path\":\"admin\",\"query\":{\"term\":{\"admin.admin_id\":5161096}}}}", nested.toJsonString());

        nested.query(bool);
        Assert.assertEquals("{\"nested\":{\"path\":\"admin\",\"query\":{\"bool\":{\"must\":[{\"term\":{\"admin.admin_id\":5161096}}]}}}}",
                nested.toJsonString());
    }

    @Test
    public void testBuilder() {
        nested.query(bool);
        Assert.assertEquals("{\"size\":10,\"query\":{\"bool\":{\"must\":[{\"nested\":{\"path\":\"admin\",\"query\":{\"bool\":{\"must\":[{\"term\":{\"admin.admin_id\":5161096}}]}}}},{\"term\":{\"kdt_id\":26203600}}]}},\"from\":0}",
                QueryBuilders.x5().addQuery(new Bool().must(nested).must(new Term("kdt_id", 26203600))).toJsonString());
    }

}
