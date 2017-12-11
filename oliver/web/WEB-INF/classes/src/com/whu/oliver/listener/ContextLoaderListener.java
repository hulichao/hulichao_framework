package com.whu.oliver.listener;

import com.whu.oliver.annotation.Controller;
import com.whu.oliver.annotation.Quatifier;
import com.whu.oliver.annotation.RequestMapping;
import com.whu.oliver.annotation.Service;

import javax.servlet.*;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by hulichao on 2017/12/9
 */
public class ContextLoaderListener implements ServletContextListener {

    //包下所有类类名
    List<String> packageNames = new ArrayList<String>();
    // 含有注解的目标类 ，键是注解声明，值是对应完整类名，
    //如 loginController com.whu.oliver.controller.LoginController
    //   loginService  com.whu.oliver.service.LoginServiceImpl
    Map<String, Object> instanceMap = new HashMap<String, Object>();

    //全方法名映射，跟方法,实际调用只是用了方法，还要传对象，所以可以直接放入对象
    //如 /oliver/logincontroller/add  Method
    Map<String, Object> handerMap = new HashMap<String, Object>();

    public void init() throws ServletException {
        // 包扫描,获取包中的文件
        scanPackage("com.whu.oliver");
        try {
            filterAndInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 建立映射关系
        handerMap();
        // 实现注入
        ioc();
    }

    private void filterAndInstance() throws Exception {
        if (packageNames.size() <= 0) {
            return;
        }
        for (String className : packageNames) {
            Class<?> cName = Class.forName(className.replace(".class", "").trim());
            if (cName.isAnnotationPresent(Controller.class)) {
                Object instance = cName.newInstance();
                Controller controller = (Controller) cName.getAnnotation(Controller.class);
                String key = controller.value();
                instanceMap.put(key, instance);
            } else if (cName.isAnnotationPresent(Service.class)) {
                Object instance = cName.newInstance();
                Service service = (Service) cName.getAnnotation(Service.class);
                String key = service.value();
                instanceMap.put(key, instance);
            } else {
                continue;
            }
        }
    }

    private void ioc() {

        if (instanceMap.isEmpty())
            return;
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
            // 拿到里面的所有属性
            Field fields[] = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);// 可访问私有属性
                if (field.isAnnotationPresent(Quatifier.class));
                Quatifier quatifier = field.getAnnotation(Quatifier.class);
                String value = quatifier.value();
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(), instanceMap.get(value));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 扫描包下的所有文件
     *
     * @param Package
     */
    private void scanPackage(String Package) {
        URL url = this.getClass().getClassLoader().getResource("/" + replaceTo(Package));
        String pathFile = url.getFile();
        File file = new File(pathFile);
        String fileList[] = file.list();
        for (String path : fileList) {
            File eachFile = new File(pathFile + path);
            if (eachFile.isDirectory()) {
                scanPackage(Package + "." + eachFile.getName());
            } else {
                packageNames.add(Package + "." + eachFile.getName());
            }
        }
    }

    /**
     * 建立映射关系
     */
    private void handerMap() {
        if (instanceMap.size() <= 0)
            return;
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
            if (entry.getValue().getClass().isAnnotationPresent(Controller.class)) {
                Controller controller = (Controller) entry.getValue().getClass().getAnnotation(Controller.class);
                String ctvalue = controller.value();
                Method[] methods = entry.getValue().getClass().getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        RequestMapping rm = (RequestMapping) method.getAnnotation(RequestMapping.class);
                        String rmvalue = rm.value();
                        handerMap.put("/" + ctvalue + "/" + rmvalue, method);
                    } else {
                        continue;
                    }
                }
            } else {
                continue;
            }

        }
    }

    private String replaceTo(String path) {
        return path.replaceAll("\\.", "/");
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("context开始初始化。。。。");
        ServletContext context = servletContextEvent.getServletContext();
        try {
            init();
            if (this.handerMap != null && handerMap.size() > 0 && this.instanceMap != null && this.instanceMap.size() > 0){
                context.setAttribute("handerMap",this.handerMap);
                context.setAttribute("instanceMap",this.instanceMap);
            }else {
                System.out.println("读取错误。。。。");
            }
        } catch (Exception e) {
            System.out.println("初始化失败！！");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
            System.out.println("context已销毁！！");
    }
}
