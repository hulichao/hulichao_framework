package com.whu.oliver.annotation;

import java.lang.annotation.*;

/**
 * Created by hulichao on 2017/12/8
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

    String value() default "";

    String[] path() default {};
}
