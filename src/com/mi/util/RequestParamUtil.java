package com.mi.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestParamUtil {
	public static <T>T  getParams(Class<T> clazz,HttpServletRequest req) {
		T t=null;
		
		try {
			t=clazz.newInstance();
			Enumeration<String> names = req.getParameterNames();
			Method[] methods =clazz.getMethods();
			Map<String, Method> setters = new HashMap<String, Method>();
			String methodName= null;
			
			for (Method md : methods) {
				methodName =md.getName();
				setters.put(methodName, md);
			}
			
			String name=null;
			Method method =null;
			Class<?>[] types=null;
			Class<?> type =null;
			while (names.hasMoreElements()) {
				name = names.nextElement();
				methodName ="set"+name.substring(0,1).toUpperCase()+name.substring(1);
				method =setters.get(methodName);
				if (method==null) {
					continue;
				}
				types =method.getParameterTypes();
				if (types==null) {
					continue;
				}
				type=types[0];
				if (Integer.TYPE == type || Integer.class == type){
					method.invoke(t, Integer.valueOf(req.getParameter(name)));
				}else if (Double.TYPE == type || Double.class == type){
					method.invoke(t, Double.valueOf(req.getParameter(name)));
				}else if (Float.TYPE == type || Float.class == type){
					method.invoke(t, Float.valueOf(req.getParameter(name)));
				}else if (Short.TYPE == type || Short.class == type){
					method.invoke(t, Short.valueOf(req.getParameter(name)));
				}else if (Long.TYPE == type || Long.class == type){
					method.invoke(t, Long.valueOf(req.getParameter(name)));
				}else if (Boolean.TYPE == type || Boolean.class == type){
					method.invoke(t, Boolean.valueOf(req.getParameter(name)));
				}else if (Byte.TYPE == type || Byte.class == type){
					method.invoke(t, Byte.valueOf(req.getParameter(name)));
				} else {
					method.invoke(t, String.valueOf(req.getParameter(name)));
				}
			}
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public Map<String, String> upload(HttpServletRequest req) {
		Map<String,String> map = new HashMap<String, String>();
		Enumeration<String> names = req.getParameterNames();
		String name =null;
		while (names.hasMoreElements()) {
			name = names.nextElement();
				map.put(name, req.getParameter(name));
		}
		return map;
	}
	
}
