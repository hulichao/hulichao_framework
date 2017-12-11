package com.whu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by hulichao on 2017/12/4
 */
@Target(ElementType.ANNOTATION_TYPE)
public @interface MyTarget {
    String value();
}
