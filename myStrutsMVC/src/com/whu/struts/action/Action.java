package com.whu.struts.action;

import com.whu.struts.form.ActionForm;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface Action {
	String execute(HttpServletRequest request, ActionForm form, Map<String, String> map);
}