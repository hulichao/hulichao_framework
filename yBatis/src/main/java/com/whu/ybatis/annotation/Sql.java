package com.whu.ybatis.annotation;

import java.lang.annotation.*;

/**
 * 用户自定义sql 语句
 * Created by hulichao on 2017/12/18
 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Sql {
    String value();
}
