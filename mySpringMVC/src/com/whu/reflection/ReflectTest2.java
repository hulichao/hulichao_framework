package com.whu.reflection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;

/**
 * Created by hulichao on 2017/12/5
 */
public class ReflectTest2 {

    public static void main(String [] args) throws IOException {
        //要用完整个的路径，但完整的路径不是硬编码，而是运算出来的。
//        InputStream ips = new FileInputStream("com/whu/reflection/config.properties");
        ReflectTest2.class.getClassLoader().getResourceAsStream("/com/whu/reflection/config.properties");
        ReflectTest2.class.getClassLoader().getResourceAsStream("com/whu/reflection/config.properties");
        ReflectTest2.class.getClassLoader().getResourceAsStream("config.properties");
//       InputStream ips = ReflectTest2.class.getClassLoader().getResourceAsStream("com/whu/reflection/config.properties");
//        ReflectTest2.class.getResourceAsStream("/com/whu/reflection/config.properties");
//        ReflectTest2.class.getResourceAsStream("config.properties");
//        ReflectTest2.class.getResourceAsStream("com/whu/reflection/config.properties");

        Properties props = new Properties();
//        props.load(ips);
//        System.out.println(props.get("className"));
//        props.load(ips);
//        ips.close();//将指向的操作系统的对象干掉
    }
    Collection collections = new HashSet();
    ReflectPoint pt1 = new ReflectPoint(1,2);
    ReflectPoint pt2 = new ReflectPoint(5,5);
    ReflectPoint pt3 = new ReflectPoint(1,2);


}
