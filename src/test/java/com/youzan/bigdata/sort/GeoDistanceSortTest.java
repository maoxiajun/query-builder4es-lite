package com.youzan.bigdata.sort;

import com.fordeal.search.base.BaseSort;
import com.fordeal.search.builder.SortBuilder;
import com.fordeal.search.query.Sort;
import com.fordeal.search.sort.GeoDistanceSort;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by maoxiajun on 18/4/2.
 */
public class GeoDistanceSortTest {

    @Test
    public void output() {
        GeoDistanceSort sort = SortBuilder.geoDistanceSort()
                .name("location")
                .distanceType(GeoDistanceSort.DistanceType.SLOPPY_ARC)
                .unit(GeoDistanceSort.Unit.METER)
                .order(BaseSort.OrderType.ASC)
                .point(10.111, 20.111);
        String sortStr = new Sort().field(sort).toJsonString();
        assertEquals("{\"sort\":[{\"_geo_distance\":{\"unit\":\"m\",\"distance_type\":\"sloppy_arc\",\"location\":{\"lon\":20.111,\"lat\":10.111},\"order\":\"asc\"}}]}",
                sortStr);

        sort = SortBuilder.geoDistanceSort()
                .name("location")
                .point(10.111, 20.111);
        sortStr = new Sort().field(sort).toJsonString();
        assertEquals("{\"sort\":[{\"_geo_distance\":{\"unit\":\"km\",\"distance_type\":\"plane\",\"location\":{\"lon\":20.111,\"lat\":10.111},\"order\":\"desc\"}}]}",
                sortStr);
    }

}