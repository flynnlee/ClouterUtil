package com.clouter.clouterutil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XmlUtil {
	private static final Log log = LogFactory.getLog(XmlUtil.class);
	
	public static String getAttributeValue(Element element, String name){
		return element.getAttributeValue(name);
	}
	
	public static Float getAttributeFloat(Element element, String name){
		String str = getAttributeValue(element, name);
		return str == null ? null : Float.parseFloat(str);
	}
	
	public static Double getAttributeDouble(Element element, String name){
		String str = getAttributeValue(element, name);
		return str == null ? null : Double.parseDouble(str);
	}
	
	public static Byte getAttributeByte(Element element, String name){
		String str = getAttributeValue(element, name);
		return str == null ? null : Byte.parseByte(str);
	}
	
	public static Short getAttributeShort(Element element, String name){
		String str = getAttributeValue(element, name);
		return str == null ? null : Short.parseShort(str);
	}
	
	public static Integer getAttributeInt(Element element, String name){
		String str = getAttributeValue(element, name);
		return str == null ? null : Integer.parseInt(str);
	}
	
	public static Long getAttributeLong(Element element, String name){
		String str = getAttributeValue(element, name);
		return str == null ? null : Long.parseLong(str);
	}
	
	public static Element getRoot(String fileName){
		Document doc = null;
		try {
			doc = new SAXBuilder().build(new File(fileName));
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc.getRootElement();
	}
	
	public static Map<String, String> propertiesParse(Element element){
		Map<String, String> map = new HashMap<String, String>();
		if(element != null){
			for(Element propertyEle : element.getChildren("property")){
				String key = getAttributeValue(propertyEle, "key");
				String value = getAttributeValue(propertyEle, "value");
				map.put(key, value);
				if(log.isDebugEnabled()){
					log.debug("[property] key:" + key + " value:" + value);
				}
			}
		}
		
		return map;
	}
}
