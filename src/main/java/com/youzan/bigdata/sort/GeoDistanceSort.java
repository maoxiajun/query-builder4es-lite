package com.youzan.bigdata.sort;

import com.youzan.bigdata.base.Maps2;
import com.youzan.bigdata.base.BaseSort;

import java.util.Map;

/**
 * 地址位置排序
 * Created by maoxiajun on 18/4/2.
 */
public class GeoDistanceSort extends BaseSort<GeoDistanceSort> {

    /** 距离计算算法 */
    public enum DistanceType {SLOPPY_ARC, ARC, PLANE}

    /** 距离计算单位 */
    public enum Unit {METER, KILOMETER}

    private double lat;
    private double lon;

    private String distanceType = "plane";
    private String unit = "km";

    @Override
    public String name() {
        return "_geo_distance";
    }

    /**
     * 设置距离原点
     * @param lat 纬度
     * @param lon 经度
     * @return self
     */
    public GeoDistanceSort point(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        return this;
    }

    /**
     * 设置距离计算单位
     * @param unit 距离计算单位（默认km）
     * @return self
     */
    public GeoDistanceSort unit(Unit unit) {
        if (unit == Unit.METER) {
            this.unit = "m";
        }
        return this;
    }

    /**
     * 设置距离计算方法（按何种参照系计算）
     * @param type 参照系
     * @return self
     */
    public GeoDistanceSort distanceType(DistanceType type) {
        if (type == DistanceType.SLOPPY_ARC) {
            this.distanceType = "sloppy_arc";
        } else if (type == DistanceType.ARC) {
            this.distanceType = "arc";
        }
        return this;
    }

    @Override
    public Map<String, Object> output() {
        Map<String, Object> output = super.output();
        output.put(super.name(), Maps2.of("lat", this.lat, "lon", this.lon));
        output.put("unit", this.unit);
        output.put("distance_type", this.distanceType);
        return output;
    }

}
