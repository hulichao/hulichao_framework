package com.whu.oliver.servlet;

import com.whu.oliver.controller.LoginController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by hulichao on 2017/12/9
 */
public class DispatcherServlet extends HttpServlet{
    private static final long serialVersionUID = 3272421926875073088L;

    private static final String REDIRECT_PREFIX = "redirect:";
    public DispatcherServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String context = req.getContextPath();
        String path = url.replace(context, "");
        Map<String, Object> handerMap = (Map<String, Object>) req.getServletContext().getAttribute("handerMap");
        Map<String, Object> instanceMap = (Map<String, Object>) req.getServletContext().getAttribute("instanceMap");
        Method method = (Method) handerMap.get(path);
       LoginController controller = (LoginController) instanceMap.get(path.split("/")[1]);
        try {
            if (controller != null)
                ServletProcessor.processor(controller,method,req,resp);
        } catch (Exception e) {
            System.out.println("请求出错...");
            e.printStackTrace();
        }
    }
}
