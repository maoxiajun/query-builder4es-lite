package com.youzan.bigdata;

import com.fordeal.search.QueryBuilders;
import com.fordeal.search.query.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by maoxiajun on 17/8/9.
 */
public class QueryBuilderTest {

    @Test
    public void testX1() {
        Assert.assertEquals("{\"size\":10,\"query\":{\"terms\":{\"id\":[1,2,3]}},\"from\":0}",
                QueryBuilders.x1().addQuery(new Terms("id", Arrays.asList(1, 2, 3))).toJsonString());

        // count query
        Assert.assertEquals("{\"size\":0,\"query\":{\"bool\":{\"must\":[{\"term\":{\"foo\":1}}]}},\"from\":0}",
                QueryBuilders.x1().addQuery(new Bool().must(new Term("foo", 1))).size(0).from(0).toJsonString());
    }

    @Test
    public void testX5() {
        Assert.assertEquals("{\"size\":10,\"query\":{\"bool\":{\"filter\":[{\"terms\":{\"id\":[1,2,3]}}],\"minimum_should_match\":1}},\"from\":0}",
                QueryBuilders.x5().addQuery(new Bool().filter(new Terms("id", 1, 2, 3)).minimumShouldMatch(1)).toJsonString());
    }

    @Test
    public void testBuild() {
        Date date = Date.from(Instant.ofEpochSecond(1516000282));
        String queryX1 =  QueryBuilders.x1()
                .addQuery(
                        new Bool()
                                .must(new Term("sex", 1))
                                .must(new Range("age").gt(5).lt(50))
                                .must(new Range("date").lt(date))
                                .mustNot(new Term("salary", 1000))
                                .should(new Match("name", "alex"))
                                .should(new Match("name", "alex").minimumShouldMatch(100))
                                .should(new MatchPhrase("name", "alex"))
                                .minimumShouldMatch(1)
                )
                .addFilter(new Bool().must(new Term("group", 1)))
                .from(0)
                .size(10)
                .sort(new Sort().desc("time"))
                .source(new Source().includes("id", "name").excludes("time")).toJsonString();
        System.out.println(queryX1);
        Assert.assertEquals("{\"size\":10,\"query\":{\"filtered\":{\"filter\":{\"bool\":{\"must\":[{\"term\":{\"group\":1}}]}},\"query\":{\"bool\":{\"must_not\":[{\"term\":{\"salary\":1000}}],\"should\":[{\"match\":{\"name\":\"alex\"}},{\"match\":{\"name\":{\"minimum_should_match\":\"100%\",\"query\":\"alex\"}}},{\"match_phrase\":{\"name\":\"alex\"}}],\"minimum_should_match\":1,\"must\":[{\"term\":{\"sex\":1}},{\"range\":{\"age\":{\"lt\":50,\"gt\":5}}},{\"range\":{\"date\":{\"lt\":\"2018-01-15 15:11:22\"}}}]}}}},\"_source\":{\"includes\":[\"id\",\"name\"],\"excludes\":[\"time\"]},\"from\":0,\"sort\":[{\"time\":\"desc\"}]}",
                queryX1);

        String queryX5 = QueryBuilders.x5()
                .addQuery(
                        new Bool()
                                .must(new Term("sex", 1))
                                .must(new Range("age").gt(5).lt(50))
                                .must(new Range("date").lt(date))
                                .mustNot(new Term("salary", 1000))
                                .should(new Match("name", "alex"))
                                .should(new Match("name", "alex").minimumShouldMatch(100))
                                .should(new MatchPhrase("name", "alex"))
                                .minimumShouldMatch(1)
                                .filter(new Bool().must(new Term("group", 1)))
                )
                .from(0)
                .size(10)
                .sort(new Sort().desc("time"))
                .source(new Source().includes("id", "name").excludes("time")).toJsonString();
        System.out.println(queryX5);
        Assert.assertEquals("{\"size\":10,\"query\":{\"bool\":{\"must_not\":[{\"term\":{\"salary\":1000}}],\"should\":[{\"match\":{\"name\":\"alex\"}},{\"match\":{\"name\":{\"minimum_should_match\":\"100%\",\"query\":\"alex\"}}},{\"match_phrase\":{\"name\":\"alex\"}}],\"filter\":[{\"bool\":{\"must\":[{\"term\":{\"group\":1}}]}}],\"minimum_should_match\":1,\"must\":[{\"term\":{\"sex\":1}},{\"range\":{\"age\":{\"lt\":50,\"gt\":5}}},{\"range\":{\"date\":{\"lt\":\"2018-01-15 15:11:22\"}}}]}},\"_source\":{\"includes\":[\"id\",\"name\"],\"excludes\":[\"time\"]},\"from\":0,\"sort\":[{\"time\":\"desc\"}]}",
                queryX5);
    }

}
