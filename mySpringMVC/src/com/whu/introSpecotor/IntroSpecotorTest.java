package com.whu.introSpecotor;

import com.whu.reflection.ReflectPoint;
import org.apache.commons.beanutils.BeanUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Vector;

/**
 * Created by hulichao on 2017/12/5
 */
public class IntroSpecotorTest {
    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        ReflectPoint pt1 = new ReflectPoint(3,5);

        String propName = "x";

        getProperty(pt1, propName);
        ReflectPoint p2 = new ReflectPoint(9,3);
        BeanUtils.copyProperties(pt1,p2);
        System.out.println(pt1.getX());
        Collection<String> c = new Vector();
        Collection c1 = new Vector<String>();
        Class<?> y;
        Class<String> x = String.class;
//        x=y;
        y=x;
    }


    private static void getProperty(ReflectPoint pt1, String propName) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        PropertyDescriptor pd = new PropertyDescriptor(propName,pt1.getClass());

        Method methodGetX = pd.getReadMethod();
        Object retVa = methodGetX.invoke(pt1);
    }
}
