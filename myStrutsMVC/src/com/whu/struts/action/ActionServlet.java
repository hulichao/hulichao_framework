package com.whu.struts.action;

import com.whu.struts.form.ActionForm;
import com.whu.struts.form.FullForm;
import com.whu.struts.form.XmlBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ActionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得请求得key
		String path = this.getPath(request.getServletPath());
		Map<String,XmlBean> map = (Map<String, XmlBean>) this.getServletContext().getAttribute("struts");
		XmlBean xml = map.get(path);
		String formClass = xml.getFormClass();
		ActionForm form = FullForm.full(formClass,request);
		String actionType = xml.getActionType();
		Action action = null;
		String url = "";
		try{
			Class<?> clazz = Class.forName(actionType);
			action = (Action)clazz.newInstance();
			url = action.execute(request, form,xml.getActionForward());
		}catch(Exception e){
			System.out.println("严重:控制器异常。。。。。。");
		}
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	private String getPath(String servletPath){
		return servletPath.split("\\.")[0];
	}
}
