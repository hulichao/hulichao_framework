package com.whu.generic;

import com.whu.reflection.ReflectPoint;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

/**
 * Created by hulichao on 2017/12/6
 */
public class GenericTest {
    public static void main(String[] args) throws NoSuchMethodException {
        String [] s = new String[]{"abv","ss","safdf"} ;
        swap(s,1,2);
//        System.out.println(s[2]);
        Integer [] a = new Integer[]{2,34,45};
//        int [] a = new int[]{2,34,45};  // int[] 不会自动装箱，自动装箱的是int
        swap(a,1,2);
        GenericDao dao = new GenericDao();
        dao.add(new ReflectPoint(1,2));

//        Vector<Date> v1 = new Vector<Date>();
        Method applyMethod = GenericTest.class.getMethod("applyVecotr",Vector.class);//不用写泛型
        Type[] types = applyMethod.getGenericParameterTypes();
        ParameterizedType pType = (ParameterizedType)types[0];
        System.out.println(pType.getRawType());
        System.out.println(pType.getActualTypeArguments()[0]);
    }

    public static void applyVecotr(Vector<Date> v1){
    }

    //T必须是引用 类型
    private static <T> void swap(T[] a, int i,int j){
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static <T> T autoConvert(Object obj){
        T t = (T) obj;
        return t;
    }
    public static <T> void copy1(Collection<T> dest,T[] src){

    }
    public static <T> void copy2(Collection<T> dest,T[] src){

    }
}
