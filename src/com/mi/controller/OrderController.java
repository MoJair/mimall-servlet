package com.mi.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mi.biz.OrderBiz;
import com.mi.biz.impl.OrderBizImp;
import com.mi.dao.OrderDAO;
import com.mi.entity.MemberInfo;
import com.mi.entity.OrderBean;
import com.mi.util.StringUtil;


@WebServlet("/order")
public class OrderController extends BasicServlet{
	 OrderDAO dao = new OrderDAO();
	 OrderBiz biz = new OrderBizImp();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取前端操作指令
		String op=request.getParameter("op");
		//System.out.println("我进来了op="+op);
		if("addOrder".equals(op)) {//增加订单
			addOrder(request,response);
		}else if("deleteOrder".equals(op)) {//删除订单
			deleteOrder(request,response);
		}else if("queryOrder".equals(op)) {//查找订单
			queryOrder(request,response);
		}else if("findByuid".equals(op)) {//查找订单
			findByuid(request,response);
		}else if("update".equals(op)) {//查找订单
			update(request,response);
		}else if ("add".equals(op)) { // 下订单
			add(request, response);
		} else if ("findByUid2".equals(op)) { // 通过会员编号查询订单信息
			findByUid2(request, response);
		}else if( "findByPageFirst".equals(op) ) {//后台订单第一次分页会员查询
			findByPageFirst(request,response);
		}else if( "findByPage".equals(op) ) {//后台订单的条件分页查询
			findByPage(request,response);
		}else if("updateOrder".equals(op)) {//退货修改订单状态
			updateOrder(request,response);
		}
	}
	//退货修改订单状态
	private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ono = request.getParameter("ono");//订单编号
		String status = request.getParameter("status");//订单状态
		if (StringUtil.checkNull(ono, status)) {
			this.send(response, 100);// 参数不完整
			return;
		}
		int result = biz.update(ono, Integer.parseInt(status) );
		if( result > 0 ) {
			this.send(response, 200);//修改成功
			return;
		}
		this.send(response, 101);//修改失败
	}

	//条件查询
	private void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));//当前页码数
		int rows = Integer.parseInt(request.getParameter("rows"));//每页显示行数
		String ono = request.getParameter("ono");//订单编号
		String status = request.getParameter("status");//订单状态
		OrderBean t = new OrderBean();
		t.setOno(ono);
		if( status != "" ) {
			t.setStatus( Integer.parseInt(status) );
		}
		int total = biz.total(t);
		this.send(response, total, null,biz.findByPage(ono,status, page, rows));
	}

	//后台订单第一个分页
	private void findByPageFirst(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		int total = biz.total(null);
		this.send(response, total,null,biz.findByPage(null,null, page, rows));
	}

	// 通过会员编号查询订单信息 王
	private void findByUid2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("currentLoginMember");
		if (obj == null) {
			this.send(response, 100, "用户未登录", null);
			return;
		}
		MemberInfo mf = (MemberInfo) obj;
		System.out.println(biz.findByUid(mf.getUid()));
		this.send(response, 200, null, biz.findByUid(mf.getUid()));
	}

	// 下订单
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cnos = request.getParameter("cnos");// 购物车编号
		String ano = request.getParameter("ano");// 收货地址编号
		String price = request.getParameter("price");// 订单总价格
		if (StringUtil.checkNull(cnos, ano, price)) {
			this.send(response, 100, "参数不完整", null);// 参数不完整
			return;
		}
		String result = null;// 返回的订单编号
		try {
			result = biz.add(cnos, ano, price);
			if (!StringUtil.checkNull(result)) {
				this.send(response, 200, result, null);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.send(response, 101, "下订单失败", null);
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ono = request.getParameter("ono");
		System.out.println(ono);
		int status = 5;
		System.out.println("好歹我"+biz.update(ono, status));
		this.send(response, biz.update(ono, status));
		
	}
	//蒋
	private void findByuid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("currentLoginMember");
		if (obj == null) {
			this.send(response, 100, "用户未登录", null);
			return;
		}
		MemberInfo mf = (MemberInfo) obj;
		int uid = mf.getUid();
		this.send(response, biz.findByMno(uid));
	}

	private void queryOrder(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ono = request.getParameter("ono");
		if( StringUtil.checkNull(ono) ) {
			this.send(response, 101);//参数不完整
			return;
		}
		int result = biz.delete(ono);
		if( result > 0 ) {
			this.send(response, 200);//删除成功
			return;
		}
		this.send(response, 100);//删除失败

		
	}

	private void addOrder(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
}
