package com.mi.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mi.biz.IAdminBiz;
import com.mi.biz.impl.AdminBizImlp;
import com.mi.entity.Admin;
import com.mi.util.RequestParamUtil;
import com.mi.util.SessionKey;

@WebServlet("/admin")
public class AdminController extends BasicServlet {

	private static final long serialVersionUID = -8609678686664676724L;

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String op=request.getParameter("op");
	if("login".equals(op)) {//登录
		login(request,response);
	}else if("info".equals(op)) {//将当前登录用户信息返还回去
		info(request,response);
	}else if("finds".equals(op)) {//查询所有管理员
		finds(request,response);
	}else if("update".equals(op)) {//修改数据
		update(request,response);
	}
	
  }

private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
	IAdminBiz adminBiz=new AdminBizImlp();
	Admin ad = RequestParamUtil.getParams(Admin.class, request);
	int result = adminBiz.update(ad);
	if (result>0) {
		this.send(response, 200,"" ,null );
		return;
	}else {
		this.send(response, 500,"" ,null );
	}
	
}

private void finds(HttpServletRequest request, HttpServletResponse response) throws IOException {
	IAdminBiz adminBiz=new AdminBizImlp();
	List<Admin> list=adminBiz.finds();
	System.out.println(list);
	if(list.size()>0) {
		this.send(response,list );
		return;
	}
	this.send(response, 500,"" ,null );
	
	
}

private void info(HttpServletRequest request, HttpServletResponse response) throws IOException {
	 HttpSession session=request.getSession();
	Object obj  =session.getAttribute(SessionKey.CURRENTLOGINADMIN);//获取登录用户信息
	if(obj==null) {
		this.send(response, 500, "", null);
		return;
	}
	this.send(response, 200, "", obj);
	
}

private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String   aname =request.getParameter("aname");
	String   pwd =request.getParameter("pwd");
	IAdminBiz adminBiz=new AdminBizImlp();
	  Admin  ad =adminBiz.find(aname, pwd);
	  if(ad==null) {
		  this.send(response, 500, "", null);
		  return;
	  }
	  HttpSession session=request.getSession();
	  session.setAttribute(SessionKey.CURRENTLOGINADMIN, ad);//存储登录用户
	  this.send(response, 200, "", ad);
}
}
