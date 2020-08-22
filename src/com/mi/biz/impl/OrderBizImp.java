package com.mi.biz.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.mi.biz.OrderBiz;
import com.mi.dao.OrderDAO;
import com.mi.entity.OrderBean;


public class OrderBizImp implements OrderBiz{
	OrderDAO dao = new OrderDAO();

	@Override
	public List<Map<String, String>> findByMno(int uid) {
		// TODO Auto-generated method stub
		return dao.findByUid1(uid);
	}

	@Override
	public int update(String ono, int status) {
		OrderBean t = new OrderBean();
		t.setOno(ono);
		t.setStatus(status);
		
		System.out.println("大的"+dao.update(t));
		return dao.update(t);
	}

	@Override
	public String add(String cnos, String ano, String totalPrice) throws SQLException {
		return dao.add(cnos, ano, totalPrice);
	}

	@Override
	public int delete(String ono) {
		return dao.delete(ono);
	}

	@Override
	public List<Map<String, String>> findByUid(int uid) {
		return dao.findByUid(uid);
	}

	@Override
	public List<Map<String, String>> findByPage(String ono,String status,int page, int rows) {
		return dao.findByTrem(ono,status,page,rows);
	}

	@Override
	public int total(OrderBean t) {
		return dao.total(t);
	}
}
