package com.mi.util;
import java.lang.reflect.Method;

import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;

public class FileUploadUtil {
	public static  String PATH="../pics";
	private static final String ALLOWEDLIST="gif,jpg,png,jpeg,doc,docx,xls,xlsx,txt";
	private static final int TOTALFILESIZE=100*1024*1024;
	private static final int MAXFILESIZE=10*1024*1024;
	private String basepath;

	public Map<String, String> upload(PageContext pagecontext) throws Exception {
		Map<String, String>  map = new HashMap<String, String>();
		SmartUpload su = new SmartUpload();
		su.initialize(pagecontext);
		su.setAllowedFilesList(ALLOWEDLIST);
		su.setMaxFileSize(TOTALFILESIZE);
		su.setTotalMaxFileSize(TOTALFILESIZE);
		su.setCharset("utf-8");
		su.upload();
		Request req = su.getRequest();
		Enumeration<String> enums = req.getParameterNames();
		String name = null;
		while(enums.hasMoreElements()) {
			name = enums.nextElement();
			map.put(name, req.getParameter(name));
		}
		Files  files = su.getFiles();
		if ( files==null ||files.getCount()<=0 || files.getSize()<=0||files.getFile(0).getSize()<=0) {
			return map;
		}
		Collection<File> fls = files.getCollection();
		basepath = pagecontext.getRequest().getRealPath("/");
		String	FieldName = null;
		String  FileName =null;
		String temp=null;
		String pathStr="";
		
		
		for (File fl : fls) {
			if (!fl.isMissing()) {
				temp = fl.getFieldName();
				if (StringUtil.checkNull(FieldName)) {
					FieldName = temp;
				}else {
					if (!FieldName.equals(temp)) {
						map.put(FieldName, pathStr);
						pathStr="";
						FieldName = temp;
					}
				}
				FileName=PATH +"/" +new Date().getTime()+"_"+fl.getFileName();
				if (StringUtil.checkNull(pathStr)) {
					pathStr = FileName;
				}else {
					pathStr += ","+FileName;
				}
				fl.saveAs(basepath+FileName,SmartUpload.SAVE_PHYSICAL);
			}
			
		}
		map.put(FieldName,pathStr);
		return map;
	}
	public Map<String, String> uploadPic(PageContext pagecontext) throws Exception {
		Map<String, String>  map = new HashMap<String, String>();
		SmartUpload su = new SmartUpload();
		su.initialize(pagecontext);
		su.setAllowedFilesList(ALLOWEDLIST);
		su.setMaxFileSize(TOTALFILESIZE);
		su.setTotalMaxFileSize(TOTALFILESIZE);
		su.setCharset("utf-8");
		su.upload();
		
		
		Files  files = su.getFiles();
		if (files==null || files.getCount()<=0) {
			return map;
		}
		Collection<File> fls = files.getCollection();
		basepath = pagecontext.getRequest().getRealPath("/");
		String	fieldName = null;
		String  fileName =null;
		String uploadPath=null;
		
		
		for (File fl : fls) {
			if (!fl.isMissing()) {
				fieldName = fl.getFieldName();
				fileName=fl.getFileName();
				uploadPath = PATH + "/" +new Date().getTime()+"_"+fileName;
				fl.saveAs(basepath+uploadPath,SmartUpload.SAVE_PHYSICAL);
			}
			
		}
		map.put(fieldName,uploadPath);
		map.put("fileName",fileName);
		return map;
	}
	public  <T> T upload(Class<T> clazz,PageContext pagecontext) throws Exception {
		T t = clazz.newInstance();
		SmartUpload su = new SmartUpload();
		su.initialize(pagecontext);
		su.setAllowedFilesList(ALLOWEDLIST);
		su.setMaxFileSize(TOTALFILESIZE);
		su.setTotalMaxFileSize(TOTALFILESIZE);
		su.setCharset("utf-8");
		su.upload();
		//获取文件参数
		Request req = su.getRequest();
		Enumeration<String> enums = req.getParameterNames();
		//获取方法
		Method[] methods = clazz.getMethods();
		Map<String, Method> setters = new HashMap<String, Method>();
		String methodName =null;
		for (Method md : methods) {
			methodName =md.getName();
			setters.put(methodName, md);
		}
		
		String name = null;
		Class<?>[] types =null;
		Class<?> type =null;
		Method method =null;
		
		
		while(enums.hasMoreElements()) {
			name = enums.nextElement();
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
				method.invoke(t,Integer.valueOf(req.getParameter(name)) );
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
				method.invoke(t,String.valueOf(req.getParameter(name)));
			}
		}
		Files  files = su.getFiles();
		if (files==null || files.getCount()<=0 || files.getSize()<=0||files.getFile(0).getSize()<=0) {
			return t;
		}
		Collection<File> fls = files.getCollection();
		basepath = pagecontext.getRequest().getRealPath("/");
		String	FieldName = null;
		String  FileName =null;
		String temp=null;
		String pathStr="";
		
		
		for (File fl : fls) {
			if (!fl.isMissing()) {
				temp = fl.getFieldName();
				if (StringUtil.checkNull(FieldName)) {
					FieldName = temp;
				}else {
					if (!FieldName.equals(temp)) {
						name = enums.nextElement();
						methodName ="set"+FieldName.substring(0,1).toUpperCase()+name.substring(1);
						method =setters.get(methodName);
						if (method==null) {
							continue;
						}
						types =method.getParameterTypes();
						if (types==null) {
							continue;
						}
						method.invoke(t,pathStr);
						pathStr="";
						FieldName = temp;
					}
				}
				FileName=PATH +"/" +new Date().getTime()+"_"+fl.getFileName();
				if (StringUtil.checkNull(pathStr)) {
					pathStr = FileName;
				}else {
					pathStr += ","+FileName;
				}
				fl.saveAs(basepath+FileName);
			}
			
		}
		methodName ="set"+FieldName.substring(0,1).toUpperCase()+FieldName.substring(1);
		method =setters.get(methodName);
		if (method==null) {
			return t;
		}
		types =method.getParameterTypes();
		if (types==null) {
			return t;
		}
		method.invoke(t,pathStr);
		return t;
	}
}
