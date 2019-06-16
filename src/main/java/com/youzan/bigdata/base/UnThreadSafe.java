package com.youzan.bigdata.base;

import java.lang.annotation.*;

/**
 * 非线程安全
 * Created by maoxiajun on 18/1/15.
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Inherited
@Target(ElementType.TYPE)
public @interface UnThreadSafe {
}
