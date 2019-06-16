package com.youzan.bigdata.aggregation;

import com.youzan.bigdata.QueryBuilders;
import com.youzan.bigdata.query.Bool;
import com.youzan.bigdata.query.Term;
import com.youzan.bigdata.query.Terms;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by maoxiajun on 18/1/15.
 */
public class TermsTest {

    private TermsAgg terms;

    @Before
    public void setUp() {
        terms = new TermsAgg("group_by_seller").field("seller_id");
    }

    @Test
    public void testBuild() {
        assertEquals("{\"group_by_seller\":{\"terms\":{\"field\":\"seller_id\"}}}",
                terms.toJsonString());
    }

    @Test
    public void testSize() {
        TermsAgg terms1 = new TermsAgg("member_count")
                .field("tag_ids")
                .size(30);
        assertEquals("{\"member_count\":{\"terms\":{\"field\":\"tag_ids\",\"size\":30}}}",
                terms1.toJsonString());
    }

    @Test
    public void testIncludeExclude() {
        TermsAgg terms1 = new TermsAgg("member_count")
                .field("tag_ids")
                .size(30)
                .include(1, 2, 3)
                .exclude(4, 5, 6);
        assertEquals("{\"member_count\":{\"terms\":{\"include\":[1,2,3],\"field\":\"tag_ids\",\"size\":30,\"exclude\":[4,5,6]}}}",
                terms1.toJsonString());
    }

    @Test
    public void testNested() {
        terms.sub(new Sum("sum_oder_count").field("order_count"))
                .sub(new Sum("sum_total_pay").field("total_pay"))
                .sub(new Sum("sum_total_income").field("total_income"))
                .size(0);
        assertEquals("{\"group_by_seller\":{\"terms\":{\"field\":\"seller_id\",\"size\":0},\"aggs\":{\"sum_total_pay\":{\"sum\":{\"field\":\"total_pay\"}},\"sum_total_income\":{\"sum\":{\"field\":\"total_income\"}},\"sum_oder_count\":{\"sum\":{\"field\":\"order_count\"}}}}}",
                terms.toJsonString());
    }

    @Test
    public void testBuilder() {
        assertEquals("{\"size\":0,\"query\":{\"match_all\":{}},\"from\":0,\"aggs\":{\"sum_total_pay\":{\"sum\":{\"field\":\"total_pay\"}},\"sum_total_income\":{\"sum\":{\"field\":\"total_income\"}},\"sum_oder_count\":{\"sum\":{\"field\":\"order_count\"}}}}",
                QueryBuilders.x5().addAggs(new Sum("sum_oder_count").field("order_count"))
                        .addAggs(new Sum("sum_total_pay").field("total_pay"))
                        .addAggs(new Sum("sum_total_income").field("total_income")).toJsonString());
    }

    @Test
    public void testTerms() {
        System.out.println(
                QueryBuilders.x5().addQuery(new Bool().must(new Term("kdt_id", 63077)).must(new Terms("tax_class_code", 1, 2, 3, 4)))
                        .from(0)
                        .size(0)
                        .addAggs(new TermsAgg("tax_class_code_list").field("tax_class_code").size(10))
                        .toJsonString()
        );
        System.out.println(
                QueryBuilders.x1().addQuery(new Bool().must(new Terms("fx_class1", 1, 2, 3, 4, 5, 6)))
                        .from(0)
                        .size(0)
                        .addAggs(new TermsAgg("fx_class1_count").field("fx_class1").size(10))
                        .toJsonString()
        );
    }

}
