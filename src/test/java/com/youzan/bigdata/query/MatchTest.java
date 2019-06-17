package com.youzan.bigdata.query;

import com.fordeal.search.query.Match;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by maoxiajun on 17/8/16.
 */
public class MatchTest {

    @Test
    public void testX1() {
        Match match = new Match("foo", "bar");
        Assert.assertEquals("{\"match\":{\"foo\":\"bar\"}}", match.toJsonString());

        match.minimumShouldMatch(75);
        Assert.assertEquals("{\"match\":{\"foo\":{\"minimum_should_match\":\"75%\",\"query\":\"bar\"}}}", match.toJsonString());

        match.boost(3);
        Assert.assertEquals("{\"match\":{\"foo\":{\"minimum_should_match\":\"75%\",\"boost\":3.0,\"query\":\"bar\"}}}", match.toJsonString());
    }

}
