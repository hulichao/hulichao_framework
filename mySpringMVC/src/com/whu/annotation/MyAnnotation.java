package com.whu.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by hulichao on 2017/12/4
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String hello() default "hulichao";
    String world();
}
