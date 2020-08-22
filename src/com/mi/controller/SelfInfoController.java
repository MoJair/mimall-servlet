package com.mi.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mi.biz.SelfInfoBiz;
import com.mi.biz.impl.SelfInfoBizImpl;
import com.mi.dao.DBHelper;
import com.mi.entity.AddrInfo;
import com.mi.entity.MemberInfo;
import com.mi.util.RequestParamUtil;

@WebServlet("/selfinfo")
public class SelfInfoController extends BasicServlet{
	private static final long serialVersionUID = -41421390260112475L;
	
	String auid;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		if("findByUid".equals(op)) {
			findByUid(request, response);
		} else if ("saveinfo".equals(op)) {
			saveinfo(request, response);
		} else if ("findAddrByUid".equals(op)) {
			findAddrByUid(request, response);
		} else if ("saveaddr".equals(op)) {
			saveaddr(request, response);
		} else if ("findAll".equals(op)) {
			findAll(request, response);
		}
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SelfInfoBiz selfInfoBiz = new SelfInfoBizImpl();
		this.send(response, selfInfoBiz.findAll());
	}

	private void saveaddr(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AddrInfo addr = RequestParamUtil.getParams(AddrInfo.class, request);
		SelfInfoBiz selfInfoBiz = new SelfInfoBizImpl();
		this.send(response, 203, "", selfInfoBiz.updateaddr(addr));
		
	}

	private void findAddrByUid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uid = request.getParameter("uid");
		auid = uid;
		System.out.println("地址信息" + uid);
		SelfInfoBiz selfInfoBiz = new SelfInfoBizImpl();
		System.out.println("ABC" + selfInfoBiz);
		AddrInfo selfInfo = selfInfoBiz.findAddrByUid(uid);
		if(selfInfo == null) {
			addAddr();
		}
		this.send(response, 202, "", selfInfoBiz.findAddrByUid(uid));
	}

	private void saveinfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MemberInfo type = RequestParamUtil.getParams(MemberInfo.class, request);
		SelfInfoBiz selfInfoBiz = new SelfInfoBizImpl();
		this.send(response, 201, "", selfInfoBiz.update(type));
	}

	private void findByUid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uid = request.getParameter("uid");
		System.out.println("个人信息" + uid);
		SelfInfoBiz selfInfoBiz = new SelfInfoBizImpl();
		System.out.println("abc" + selfInfoBiz);
		this.send(response, 200, "", selfInfoBiz.findByUid(uid));
	}

	private int addAddr() {
		DBHelper db = new DBHelper();
		String uid= auid;
		String sql = "insert into addrInfo values ('20200822',?,'','', '', '', '','','','1','1')";
		return db.update(sql, uid);
	}
}
