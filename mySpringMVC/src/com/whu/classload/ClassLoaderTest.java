package com.whu.classload;

import java.util.Date;

/**
 * Created by hulichao on 2017/12/6
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {
        MyClassLoader loader1 = new MyClassLoader("loader1");

        loader1.setPath("J:\\Hu_work\\tools\\idea_workpace\\hulichao_framework\\mySpringMVC\\src\\com\\whu\\classload\\MyClassLoaderAttachMent.java");

        MyClassLoader loader2 = new MyClassLoader(loader1,"loader2");

        loader2.setPath("J:\\Hu_work\\tools\\idea_workpace\\hulichao_framework\\mySpringMVC\\src\\com\\whu\\classload\\MyClassLoaderAttachMent.java");

        MyClassLoader loader3 = new MyClassLoader(null,"loader3");

        loader3.setPath("J:\\Hu_work\\tools\\idea_workpace\\hulichao_framework\\mySpringMVC\\src\\com\\whu\\classload\\MyClassLoaderAttachMent.java");
        test(loader2);

    }

    public static void test(ClassLoader loader) throws Exception{
        Class clazz = loader.loadClass("com.whu.classload.MyClassLoaderAttachMent");

        Object object = clazz.newInstance();
    }
}
