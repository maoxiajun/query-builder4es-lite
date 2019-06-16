package com.youzan.bigdata.query;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by maoxiajun on 2018/10/29.
 */
public class ScriptTest {

    @Test
    public void testX5() {
        Script script = new Script();
        script.script("doc['created_at'].value >= params.gte && doc['created_at'].values <= params.lte")
                .params("gte", 100, "lte", 200)
                .boost(1);
        assertEquals("{\"script\":{\"boost\":1.0,\"script\":{\"inline\":\"doc['created_at'].value >= params.gte && doc['created_at'].values <= params.lte\",\"lang\":\"painless\",\"params\":{\"gte\":100,\"lte\":200}}}}",
                script.toJsonString());
    }

}