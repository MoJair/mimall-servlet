package com.mi.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;
import com.mi.biz.GoodsInfoBiz;
import com.mi.biz.impl.GoodsInfoBizImpl;
import com.mi.entity.GoodsInfo;
import com.mi.util.FileUploadUtil;
import com.mi.util.RequestParamUtil;

@WebServlet("/goods")
public class GoodsInfoController extends BasicServlet{
	private static final long serialVersionUID = -2265550230620928770L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String op = request.getParameter("op");
		
		if("finds".equals(op)) {
			finds(request, response);
		} else if ("findByPage".equals(op)) {
			findByPage(request, response);
		} else if ("findByCondition".equals(op)) {
			findByCondition(request, response);
		} else if ("add".equals(op)) {
			add(request, response);
		} else if ("findIndex".equals(op)) {
			findIndex(request, response);
		} else if ("findByPname".equals(op)) {
			findByPname(request, response);
		} else if ("findByPid".equals(op)) {
			findByPid(request, response);
		}
	}

	private void findByPid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		GoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		int pid = Integer.parseInt(request.getParameter("pid"));
		this.send(response,200,"",goodsInfoBiz.findByPid(pid));
	}

	private void findByPname(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pname = request.getParameter("pname");
		String tno = request.getParameter("tno");
		GoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		List<GoodsInfo> list = goodsInfoBiz.findByPname(tno, pname);
		if (list==null ||list.size()<=0) {
			this.send(response, 500,"", null);
			return;
		}
		this.send(response,200,"",list);
	}

	private void findIndex(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		GoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		this.send(resp,200,"", goodsInfoBiz.findIndex());
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			PageContext pagecontext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true,2048, true);
			FileUploadUtil  fileUploadUtil = new FileUploadUtil();
			Map<String, String> map = fileUploadUtil.upload(pagecontext);
			GoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
			this.send(response, goodsInfoBiz.add(map));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void findByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		String tno = request.getParameter("tno");
		String pname = request.getParameter("pname");
		GoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		this.send(response, goodsInfoBiz.findByCondition(tno, pname, page, rows));
	}

	private void findByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		GoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		this.send(response, goodsInfoBiz.findByPage(page, rows));
		
	}

	private void finds(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		GoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		this.send(response, goodsInfoBiz.finds(page, rows));
	}

}
