package com.whu.oliver.servlet;

import com.whu.oliver.annotation.JSON;
import com.whu.oliver.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hulichao on 2017/12/10
 */
public class ServletProcessor {
    private static final String REDIRECT_PREFIX = "redirect:";
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String SET = "set";

    public static void processor(Object bean, Method method, HttpServletRequest request, HttpServletResponse response) {
        try {
            Object[] params = bindParameters(method, request, response);
            Object result = method.invoke(bean, params);
            if (method.getAnnotation(JSON.class) != null) {
                PrintWriter writer = response.getWriter();
                writer.write(com.alibaba.fastjson.JSON.toJSONString(result));
                writer.flush();
                writer.close();
                return;
            }
            if (method.getReturnType().equals(String.class)) {
                String pageUrl = (String) result;
                if (pageUrl.startsWith(REDIRECT_PREFIX)) {
                    response.sendRedirect(request.getContextPath() + pageUrl.replace(REDIRECT_PREFIX, ""));
                } else {
                    Map<String, Object> returnMap = getReturnMap(params);
                    if (returnMap != null) {
                        for (Map.Entry<String, Object> entry : returnMap.entrySet()) {
                            request.setAttribute(entry.getKey(), entry.getValue());
                        }
                    }
                    request.getRequestDispatcher(pageUrl).forward(request, response);
                }
            }
        } catch (Exception e) {
            System.out.println("servletProcessor执行出错");
        }
    }

    private static Map<String, Object> getReturnMap(Object[] params) {
        Map<String, Object> result = null;
        if (params != null && params.length > 0) {
            for (Object object : params) {
                if (object != null && Map.class.isAssignableFrom(object.getClass())) {
                    result = (Map<String, Object>) object;
                }
            }
        }
        return result;
    }

    /**

     * 绑定controller方法里的参数数据

     */
    private static Object[] bindParameters(Method method, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Object[] result = new Object[method.getParameterCount()];
        Parameter[] parameters = method.getParameters();
        if (parameters != null && parameters.length > 0) {
            for (int i = 0; i < parameters.length; i++) {
                Class clazz = parameters[i].getType();
                Type type = parameters[i].getParameterizedType();
                Param param = parameters[i].getAnnotation(Param.class);
                String paramName = param == null ? null : param.value();
                result[i] = createParameter(clazz, type, request, response, paramName);
            }
        }
        return result;
    }

    private static Object createParameter(Class clazz, Type type, HttpServletRequest request,
                                          HttpServletResponse response, String paramName) throws Exception {
        if (paramName != null) {
            String[] paramValues = (String[]) request.getParameterMap().get(paramName);
            if (paramValues == null || paramValues.length == 0) {
                return null;
            }
            if (List.class.isAssignableFrom(clazz)) {
                Type[] types = ((ParameterizedType) type).getActualTypeArguments();
                Class parameterizedClass = (Class) types[0];
                List result = new ArrayList();
                request.getParameterMap();
                for (String paramValue : paramValues) {
                    result.add(getPrimitive(parameterizedClass, paramValue));
                }
                return result;
            }
            return getPrimitive(clazz, paramValues[0]);
        } else {
            if (Map.class == clazz) {
                return new HashMap<String, Object>();
            } else if (HttpServletRequest.class == clazz) {
                return request;
            } else if (HttpServletResponse.class == clazz) {
                return response;
            } else {
                Object result = null;
                Map<String, String[]> parameterMap = request.getParameterMap();
                Map<String, Field> fieldMap = new HashMap<String, Field>();
                fillAllFields(fieldMap, clazz);
                Map<String, Method> methodMap = new HashMap<String, Method>();
                for (Method method : clazz.getMethods()) {
                    if (!method.getDeclaringClass().equals(java.lang.Object.class)) {
                        methodMap.put(method.getName(), method);
                    }
                }
                for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                    String parameterName = entry.getKey();
                    String[] paramValue = entry.getValue();
                    if (parameterName.indexOf(".") == -1) {
                        if (fieldMap.containsKey(entry.getKey())) {
                            if (result == null) {
                                result = clazz.newInstance();
                            }
                            String setMethod = SET + parameterName.substring(0, 1).toUpperCase()
                                    + parameterName.substring(1);
                            Method method = methodMap.get(setMethod);
                            if (method != null) {
                                method.invoke(result, getPrimitive(method.getParameterTypes()[0],
                                        paramValue != null && paramValue.length > 0 ? paramValue[0] : null));
                            }
                        }
                    } else {// 属性是对象预留


                    }
                }
                return result;
            }
        }
    }

    private static Object getPrimitive(Class clazz, String paramValue) throws Exception {
        if (clazz == Long.class || clazz == Long.TYPE) {
            return paramValue == null ? null : Long.valueOf(paramValue);
        } else if (clazz == Integer.class || clazz == Integer.TYPE) {
            return paramValue == null ? null : Integer.valueOf(paramValue);
        } else if (clazz == Byte.class || clazz == Byte.TYPE) {
            return paramValue == null ? null : Byte.valueOf(paramValue);
        } else if (clazz == Short.class || clazz == Short.TYPE) {
            return paramValue == null ? null : Short.valueOf(paramValue);
        } else if (clazz == Float.class || clazz == Float.TYPE) {
            return paramValue == null ? null : Float.valueOf(paramValue);
        } else if (clazz == Double.class || clazz == Double.TYPE) {
            return paramValue == null ? null : Double.valueOf(paramValue);
        } else if (clazz == Boolean.class || clazz == Boolean.TYPE) {
            return paramValue == null ? null : Boolean.valueOf(paramValue);
        } else if (java.util.Date.class == clazz) {
            return paramValue == null ? null : sdf.parse(paramValue);
        } else if (clazz == String.class) {
            return paramValue;
        }
        return null;
    }

    private static void fillAllFields(Map<String, Field> fieldMap, Class clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            if (!fieldMap.containsKey(field.getName())) {
                fieldMap.put(field.getName(), field);
            }
        }
        Class superClass = clazz.getSuperclass();
        if (superClass != null) {
            fillAllFields(fieldMap, superClass);
        }
    }
}
