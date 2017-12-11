package com.whu.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by hulichao on 2017/12/4
 */
public class MyReflection {
    public static void main (String [] args) throws Exception {
        Class<MyTest> c = MyTest.class;
            MyTest myTest = c.newInstance();
            Method method = c.getMethod("output",new Class[]{});
            if(method.isAnnotationPresent(MyAnnotation.class)){
                method.invoke(myTest);
                MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
                System.out.println(annotation.hello());
            }
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation :annotations){
                System.out.println(annotation.annotationType().getName());
            }
    }
}
