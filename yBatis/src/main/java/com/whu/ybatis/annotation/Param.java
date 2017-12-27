package com.whu.ybatis.annotation;

import java.lang.annotation.*;

/**
 * 参数使用的注解
 * Created by hulichao on 2017/12/18
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Param {
    String value();
}
