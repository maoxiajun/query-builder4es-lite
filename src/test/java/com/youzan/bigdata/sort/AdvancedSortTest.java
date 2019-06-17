package com.youzan.bigdata.sort;

import com.fordeal.search.query.Sort;
import com.fordeal.search.sort.AdvancedSort;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by maoxiajun on 2018/6/12.
 */
public class AdvancedSortTest {

    @Test
    public void testMissing() {
        AdvancedSort ad = new AdvancedSort();
        ad.name("xxx_id")
                .missing(10);
        String sortStr = new Sort().field(ad).toJsonString();
        assertEquals(sortStr, "{\"sort\":[{\"xxx_id\":{\"missing\":10,\"order\":\"desc\"}}]}");
    }

}