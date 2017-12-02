package com.whu.struts.form;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Map;

public class ActionListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("信息：系统已注销。。。。。");

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext context = arg0.getServletContext();
		String xmlPath = arg0.getServletContext().getInitParameter("struts-config");
		String tomcatPath = arg0.getServletContext().getRealPath("/");
		try{
			System.out.println("解析路径："+tomcatPath+xmlPath);
			Map<String ,XmlBean> map = StrutsXml.parseStrutsXml(tomcatPath+xmlPath);
//			System.out.println("配置文件map:"+map.get("/user"));
			context.setAttribute("struts", map);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("异常：xmlPath读取错误。。。。");
		}
		System.out.println("信息：系统已经加载完成。。。。");
	}

}
