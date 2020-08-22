package com.mi.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mi.biz.ICartInfoBiz;
import com.mi.biz.impl.CartInfoBizImpl;
import com.mi.entity.CartInfo;
import com.mi.entity.MemberInfo;
import com.mi.util.RequestParamUtil;



@WebServlet("/cart")
public class CartInfoController extends BasicServlet {
	private static final long serialVersionUID = 4228847704374506018L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String op = req.getParameter("op");
		if (op.equals("info")) {
			info(req,resp);
		}else if (op.equals("update")) {
			update(req,resp);
		}else if (op.equals("add")) {
			add(req,resp);
		}else if (op.equals("find")) {
			find(req,resp);
		}else if (op.equals("findByCnos")) {
			findByCnos(req,resp);
		}else if (op.equals("delete")) {
			delete(req,resp);
		}
		
    }
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String cno = req.getParameter("cno");
		ICartInfoBiz cartInfoBiz = new CartInfoBizImpl();
		int result = cartInfoBiz.delete(cno);
		if (result>0) {
			this.send(resp, 200,"",null);
		}else {
			this.send(resp, 500,"",null);
		}
		
	}
	private void findByCnos(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String cnos = req.getParameter("cnos");
		ICartInfoBiz cartInfoBiz = new CartInfoBizImpl();
		this.send(resp, 200,"",cartInfoBiz.findByCnos(cnos));
	}
	private void find(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("currentLoginMember");
		if (obj == null) {
			this.send(resp, 500,"",null);
			return;
		}
		ICartInfoBiz cartInfoBiz = new CartInfoBizImpl();
		MemberInfo mf = (MemberInfo) obj;
		this.send(resp, 200,"",cartInfoBiz.finds(String.valueOf(mf.getUid())));
	}
	private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CartInfo cf = RequestParamUtil.getParams(CartInfo.class, req);
		ICartInfoBiz cartInfoBiz = new CartInfoBizImpl();
		String cno = UUID.randomUUID().toString().replace("-", "");
		cf.setCno(cno);
		int result = cartInfoBiz.add(cf);
		
		if (result>0) {
			this.send(resp, 200,cno,null);
		}else {
			this.send(resp, 500,"",null);
		}
	}
	private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String cno = req.getParameter("cno");
		int num = Integer.parseInt(req.getParameter("num"));
		ICartInfoBiz cartInfoBiz = new CartInfoBizImpl();
		int result = cartInfoBiz.updateNum(cno, num);
		if (result>0) {
			this.send(resp, 200,"",null);
		}else {
			this.send(resp, 500,"",null);
		}
		
	}
	private void info(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("currentLoginMember");
		if (obj == null) {
			this.send(resp, 500,"",null);
			return;
		}
		ICartInfoBiz cartInfoBiz = new CartInfoBizImpl();
		MemberInfo mf = (MemberInfo) obj;
		this.send(resp, 200,"",cartInfoBiz.findCart(String.valueOf(mf.getUid())));
	}
	
}
