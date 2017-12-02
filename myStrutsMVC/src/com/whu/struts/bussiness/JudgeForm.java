package com.whu.struts.bussiness;


import com.whu.struts.form.ActionForm;

public class JudgeForm extends ActionForm {
	public JudgeForm(){}
	
	private String name = "";
	
	private String pass ="";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
