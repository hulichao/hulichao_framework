package com.whu.annotation;

/**
 * Created by hulichao on 2017/12/4
 */
public @interface AnnotationTest {
    EnumTest value() default EnumTest.hello;
    String value2();
    Class []c();

}

enum EnumTest{
    hello,world,welcome
}
