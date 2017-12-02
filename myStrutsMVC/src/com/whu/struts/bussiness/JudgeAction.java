package com.whu.struts.bussiness;


import com.whu.struts.action.Action;
import com.whu.struts.form.ActionForm;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class JudgeAction implements Action {

	@Override
	public String execute(HttpServletRequest request, ActionForm form, Map<String, String> actionForward) {
		String url = "error";
		JudgeForm myform = (JudgeForm)form;
		if("admin".equals(myform.getName())){
			url = "success";
		}
		return actionForward.get(url);
	}

}
