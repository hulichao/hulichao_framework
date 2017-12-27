package com.whu.ybatis.annotation;

import java.lang.annotation.*;

/**
 * 返回的泛型类型，如果没有则默认是java.util.Map
 * Created by hulichao on 2017/12/18
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ResultType {
    Class<?> value();
}

