package com.youzan.bigdata.query;

import com.youzan.bigdata.QueryBuilders;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by maoxiajun on 17/8/16.
 */
public class HighLightTest {

    @Test
    public void testX1() {
        HighLight highLight = new HighLight()
                .preTags("<b>")
                .postTags("</b>")
                .field(new HighLightField("foo"))
                .field(new HighLightField("bar").highLightQuery(new Match("bar", "abc")));

        Assert.assertEquals("{\"highlight\":{\"pre_tags\":[\"<b>\"],\"post_tags\":[\"</b>\"],\"fields\":[{\"foo\":{}},{\"bar\":{\"highlight_query\":{\"match\":{\"bar\":\"abc\"}}}}]}}",
                highLight.toJsonString());
    }

    @Test
    public void testFullQuery() {
        String query = QueryBuilders.x5().from(0).size(20)
                .source(new Source().includes("tid", "subject"))
                .addQuery(new Bool()
                        .filter(new Bool().must(new Term("is_hide", 0)))
                        .should(new Match("subject_ik", "营销工具").minimumShouldMatch(75))
                        .should(new MatchPhrase("subject_ik", "营销工具").boost(5000).slop(5))
                )
                .highlight(new HighLight()
                        .preTags("<b>")
                        .postTags("</b>")
                        .field(new HighLightField("subject").highLightQuery(new MatchPhrase("subject", "营销工具")))
                )
                .toJsonString();
        Assert.assertEquals("{\"highlight\":{\"pre_tags\":[\"<b>\"],\"post_tags\":[\"</b>\"],\"fields\":[{\"subject\":{\"highlight_query\":{\"match_phrase\":{\"subject\":\"营销工具\"}}}}]},\"size\":20,\"query\":{\"bool\":{\"should\":[{\"match\":{\"subject_ik\":{\"minimum_should_match\":\"75%\",\"query\":\"营销工具\"}}},{\"match_phrase\":{\"subject_ik\":{\"boost\":5000.0,\"query\":\"营销工具\",\"slop\":5}}}],\"filter\":[{\"bool\":{\"must\":[{\"term\":{\"is_hide\":0}}]}}]}},\"_source\":{\"includes\":[\"tid\",\"subject\"]},\"from\":0}",
                query);
    }

}
