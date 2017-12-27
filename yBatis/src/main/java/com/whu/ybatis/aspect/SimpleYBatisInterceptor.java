package com.whu.ybatis.aspect;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * yBatis拦截器实现
 * Created by hulichao on 2017/12/21
 */
public class SimpleYBatisInterceptor implements YBatisInterceptor {

    public boolean onInsert(Field[] fields, Object obj) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        for (int j = 0; j < fields.length; j++) {
            fields[j].setAccessible(true);
            String fieldName = fields[j].getName();
            if ("createBy".equals(fieldName)) {
                map.put("createBy", "hulichao");
            }
            if ("createDate".equals(fieldName)) {
                map.put("createDate", new Date());
            }
        }
        try {
            setFieldValue(map, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean onUpdate(Field[] fields, Object obj) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        for (int j = 0; j < fields.length; j++) {
            fields[j].setAccessible(true);
            String fieldName = fields[j].getName();
            if ("updateBy".equals(fieldName)) {
                map.put("updateBy", "hulichao");
            }
            if ("updateDate".equals(fieldName)) {
                map.put("updateDate", new Date());
            }
        }
        try {
            setFieldValue(map, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 设置bean 属性值
     *
     * @param map
     * @param bean
     * @throws Exception
     */
    public static void setFieldValue(Map<Object, Object> map, Object bean) throws Exception {
        Class<?> cls = bean.getClass();
        Method methods[] = cls.getDeclaredMethods();
        Field fields[] = cls.getDeclaredFields();

        for (Field field : fields) {
            String fldtype = field.getType().getSimpleName();
            String fldSetName = field.getName();
            String setMethod = parseSetName(fldSetName);
            if (!checkMethod(methods, setMethod)) {
                continue;
            }
            if(!map.containsKey(fldSetName)){continue;}
            Object value = map.get(fldSetName);
            System.out.println(value.toString());
            Method method = cls.getMethod(setMethod, field.getType());
            System.out.println(method.getName());
            if (null != value) {
                if ("String".equals(fldtype)) {
                    method.invoke(bean, (String) value);
                } else if ("Double".equals(fldtype)) {
                    method.invoke(bean, (Double) value);
                } else if ("int".equals(fldtype)) {
                    int val = Integer.valueOf((String) value);
                    method.invoke(bean, val);
                }else{
                    method.invoke(bean, value);
                }
            }

        }
    }

    /**
     * 拼接某属性set 方法
     *
     * @param fldname
     * @return
     */
    public static String parseSetName(String fldname) {
        if (StringUtils.isEmpty(fldname)){
            return null;
        }
        String pro = "set" + fldname.substring(0, 1).toUpperCase() + fldname.substring(1);
        return pro;
    }

    /**
     * 判断该方法是否存在
     *
     * @param methods
     * @param met
     * @return
     */
    public static boolean checkMethod(Method methods[], String met) {
        if (null != methods) {
            for (Method method : methods) {
                if (met.equals(method.getName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
