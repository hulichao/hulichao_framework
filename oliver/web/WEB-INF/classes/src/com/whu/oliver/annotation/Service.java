package com.whu.oliver.annotation;

import java.lang.annotation.*;

/**
 * Created by hulichao on 2017/12/9
 */

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
    String value() default "";
}
