package com.mi.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mi.biz.GoodsTypeBiz;
import com.mi.biz.impl.GoodsTypeBizImpl;
import com.mi.entity.GoodsType;
import com.mi.util.RequestParamUtil;

@WebServlet("/type")
public class GoodsTypeController extends BasicServlet{
	private static final long serialVersionUID = -5689476184957604044L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		if("findAll".equals(op)) {
			findAll(request, response);
		} else if ("add".equals(op)) {
			add(request, response);
		} else if ("update".equals(op)) {
			update(request, response);
		} else if ("delete".equals(op)) {
			delete(request, response);
	}

}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tno = request.getParameter("tno");
		GoodsTypeBiz goodsTypeBiz = new GoodsTypeBizImpl();
		this.send(response, goodsTypeBiz.delete(tno));
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		GoodsType type = RequestParamUtil.getParams(GoodsType.class, request);
		GoodsTypeBiz goodsTypeBiz = new GoodsTypeBizImpl();
		this.send(response, goodsTypeBiz.update(type));
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		GoodsType type = RequestParamUtil.getParams(GoodsType.class, request);
		GoodsTypeBiz goodsTypeBiz = new GoodsTypeBizImpl();
		this.send(response, goodsTypeBiz.add(type));
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		GoodsTypeBiz goodsTypeBiz = new GoodsTypeBizImpl();
		this.send(response, goodsTypeBiz.findAll());
	}
	
}
