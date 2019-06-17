package com.youzan.bigdata.query;

import com.fordeal.search.query.MatchPhrase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by maoxiajun on 17/8/16.
 */
public class MatchPhraseTest {

    @Test
    public void testX1() {
        MatchPhrase phrase = new MatchPhrase("foo", "bar");
        Assert.assertEquals("{\"match_phrase\":{\"foo\":\"bar\"}}", phrase.toJsonString());

        phrase.slop(5);
        Assert.assertEquals("{\"match_phrase\":{\"foo\":{\"query\":\"bar\",\"slop\":5}}}", phrase.toJsonString());

        phrase.boost(2);
        Assert.assertEquals("{\"match_phrase\":{\"foo\":{\"boost\":2.0,\"query\":\"bar\",\"slop\":5}}}", phrase.toJsonString());
    }

}
