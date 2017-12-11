package com.whu.annotation;

/**
 * Created by hulichao on 2017/12/4
 */
public class MyTest {
    @MyAnnotation(hello="hello_hulichao",world = "shanghai")
    @Deprecated
    @SuppressWarnings("unchecked")
    public void output(){
        System.out.println("hello world");
    }
}
