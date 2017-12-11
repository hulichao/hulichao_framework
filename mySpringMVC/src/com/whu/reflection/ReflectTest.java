package com.whu.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;

/**
 * Created by hulichao on 2017/12/4
 * 要明白框架与工具类的区别，框架是房子，工具类是锁，而普通的类是房子内的家具等
 * 框架调用 普通类，工具类是用来被调用的
 */
public class ReflectTest{
    public static void main(String[] args) throws Exception{
        String str1 = "abc";
        Class cls1 = str1.getClass();
        Class cls2 = String.class;
        Class cls3 = Class.forName("java.lang.String");
        System.out.println(cls1 == cls2);
        System.out.println(cls1.isPrimitive());
        System.out.println(int.class.isPrimitive());
        System.out.println(int.class == Integer.class);
        System.out.println(int[].class.isArray());
        System.out.println(void.class.isPrimitive());

        Constructor constructor = String.class.getConstructor(StringBuffer.class);
        String str2 = (String) constructor.newInstance(new StringBuffer("123"));
        System.out.println(str2);
        System.out.println();
        Class.forName("java.lang.String");
    }

    private static void printObject(Object object){
          Class clazz =object.getClass();
          if(clazz.isArray()){
                int len = Array.getLength(object);
                for (int i= 0;i<len;i++){
                      System.out.println(Array.get(object,i));
                }
          }else{
                System.out.println(object);
          }
    }
}
