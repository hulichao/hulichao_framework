package com.whu.struts.form;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

public class FullForm {
	public FullForm(){}
	
	public static ActionForm full(String formPath,HttpServletRequest request){
		ActionForm form = null;
		try{
			Class clazz = Class.forName(formPath);
			form = (ActionForm)clazz.newInstance();
			Field []fields = clazz.getDeclaredFields();
			for(Field field:fields){
				field.setAccessible(true);
				field.set(form, request.getParameter(field.getName()));
				field.setAccessible(false);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("严重：form 转载失败！、、、、、、");
		}
		return form;
	}
}
