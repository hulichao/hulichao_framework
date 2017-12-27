package com.whu.ybatis.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 此注解功能跟Repository 类似,实现原理差不多
 * Created by hulichao on 2017/12/21
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface YDao {
}
