package com.whu.struts.form;


import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class StrutsXml {
	public  StrutsXml(){}
	
	public static Map<String,XmlBean> parseStrutsXml(String xmlConfigPath) throws Exception{
		SAXBuilder builder = new SAXBuilder();
//		Document doucument = builder.build("bin/struts-config.xml");
		Document doucument = builder.build(new File(xmlConfigPath));
		Element root = doucument.getRootElement();
		Map<String,XmlBean> rmap = new HashMap<String,XmlBean>();
		Element actionRoot = root.getChild("action-mapping");
		List<Element> actions = actionRoot.getChildren();
		String path = "";
		for(Element e:actions){
			XmlBean action = new XmlBean();
			String name = e.getAttributeValue("name");
			action.setBeanName(name);
			Element actionForm = root.getChild("formbeans");
			List<Element> form = actionForm.getChildren();
			for(Element ex:form){
				if(name.equals(ex.getAttributeValue("name"))){
					String formClass = ex.getAttributeValue("class");
					action.setFormClass(formClass);
					break;
				}
			}
			path = e.getAttributeValue("path");
			action.setPath(path);
			String type = e.getAttributeValue("type");
			action.setActionType(type);
			List<Element> foward = e.getChildren();
			Map<String,String> map = new HashMap<String,String>();
			for(Element x:foward){
				String fname = x.getAttributeValue("name");
				String fvalue = x.getAttributeValue("value");
				map.put(fname,fvalue);
			}
			action.setActionForward(map);
			rmap.put(path, action);
		}
		return rmap;
	}
	
}
