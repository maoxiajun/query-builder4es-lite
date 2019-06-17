package com.fordeal.search.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maoxiajun on 18/1/12.
 */
public class Maps2 {

    public static <K, V> Map<K, V> of() {
        return new HashMap<>();
    }

    public static <K, V> Map<K, V> of(int initSize) {
        return new HashMap<>(initSize);
    }

    public static <K, V> Map<K, V> of(K key, V value) {
        return new HashMap<K, V>() {{
            put(key, value);
        }};
    }

    public static <K, V> Map<K, V> of(K key1, V value1, K key2, V value2) {
        return new HashMap<K, V>() {{
            put(key1, value1);
            put(key2, value2);
        }};
    }

}
