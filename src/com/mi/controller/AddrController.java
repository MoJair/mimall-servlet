package com.mi.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mi.biz.AddrBiz;
import com.mi.biz.impl.AddrBizImp;
import com.mi.entity.Addrbean;
import com.mi.entity.MemberInfo;


@WebServlet("/addr")
public class AddrController extends BasicServlet{
	
	private static final long serialVersionUID = 1L;
	AddrBiz biz= new AddrBizImp();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取前端操作指令
				String op=request.getParameter("op");
				//System.out.println("我进来了op="+op);
				if("findByUid".equals(op)) {//邮箱登录
					findByUid(request,response);
				}else if("add".equals(op)) {//
					add(request,response);
				}else if("delete".equals(op)) {//
					delete(request,response);
				}else if("update".equals(op)) {//
					update(request,response);
				}else if ("findByUid2".equals(op)) {// 通过uid获取当前用户的收货地址
					findByUid2(request, response);
				}
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ano =request.getParameter("ano");
		String name = request.getParameter("name");
		String postcode = request.getParameter("postcode");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String addr = request.getParameter("addr");
		String tel = request.getParameter("tel");
		Addrbean t = new Addrbean();
		t.setAno(ano);t.setName(name);t.setPostcode(postcode);t.setProvince(province);
		
		t.setCity(city);t.setArea(area);t.setAddr(addr);t.setTel(tel);
		System.out.println(ano+"--"+name +"11"+ postcode+"2"+province);
		this.send(response, biz.update(t));
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ano = request.getParameter("ano");
		System.out.println(ano);
		Addrbean t = new Addrbean();
		t.setAno(ano);
		this.send(response, biz.delete(ano));
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("currentLoginMember");
		if (obj == null) {
			this.send(response, 500,"",null);
			return;
		}
		MemberInfo mf = (MemberInfo) obj;
		int uid = mf.getUid();
		String name = request.getParameter("name");
		String postcode = request.getParameter("postcode");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String addr = request.getParameter("addr");
		String tel = request.getParameter("tel");
		String fixedtel =null;
		long a =new Date().getTime();
		String ano=Long.toString(a); 
		Addrbean t = new Addrbean();
		t.setAno(ano);t.setName(name);t.setPostcode(postcode);t.setProvince(province);t.setCity(city);
		t.setArea(area);t.setAddr(addr);t.setTel(tel);t.setFixedtel(fixedtel);t.setUid(uid);
		System.out.println(ano);
		this.send(response, biz.add(t));
	}

	private void findByUid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int uid =  Integer.parseInt(request.getParameter("uid"));
		//System.out.println(uid);
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("currentLoginMember");
		if (obj == null) {
			this.send(response, 500,"",null);
			return;
		}
		MemberInfo mf = (MemberInfo) obj;
		int uid = mf.getUid();
		//System.out.println(uid+"dasasd");
		Addrbean t = new Addrbean();
		t.setUid(uid);
		
		this.send(response, biz.findByTrem(t));
		
	}

	// 通过uid获取当前用户的收货地址
	private void findByUid2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("currentLoginMember");
		if (obj == null) {
			this.send(response, 500,"",null);
			return;
		}
		MemberInfo mf = (MemberInfo) obj;
		this.send(response, 200, null, biz.findByUid2(mf.getUid()));
	}

}
