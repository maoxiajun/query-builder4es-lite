package com.youzan.bigdata.base;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by maoxiajun on 18/1/12.
 */
public class Maps2 {

    public static <K, V> Map<K, V> of() {
        return Maps.newHashMap();
    }

    public static <K, V> Map<K, V> of(int initSize) {
        return Maps.newHashMapWithExpectedSize(initSize);
    }

    public static <K, V> Map<K, V> of(K key, V value) {
        Map<K, V> val = Maps.newHashMap();
        val.put(key, value);
        return val;
    }

    public static <K, V> Map<K, V> of(K key, V value, K key2, V value2) {
        Map<K, V> val = Maps.newHashMap();
        val.put(key, value);
        val.put(key2, value2);
        return val;
    }

}
