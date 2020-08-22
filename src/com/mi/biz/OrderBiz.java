package com.mi.biz;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.mi.entity.OrderBean;


public interface OrderBiz {
	
	
	
	/**
	 * 添加订单 王
	 * 
	 * @param cnos       购物车编号
	 * @param ano        收货地址编号
	 * @param totalPrice 总金额
	 * @return 返回 订单编号
	 * @throws SQLException 
	 */
	public String add(String cnos, String ano, String totalPrice) throws SQLException;
	
	/**
	 * 删除订单 王
	 * 
	 * @param ono
	 * @return
	 */
	public int delete(String ono);

	/**
	 * 根据会员编号查询订单 蒋
	 * @param mno
	 * @return
	 */
	public List<Map<String, String>> findByMno(int uid);
	
	/**
	 * 根据会员编号查询订单 王
	 * 
	 * @param mno
	 * @return
	 */
	public List<Map<String, String>> findByUid(int uid);

	public int update(String ono,int status);

	/**
	 * 查询所有订单信息
	 */
	public List<Map<String, String>> findByPage(String ono,String status,int page, int rows);
	
	/**
	 * 条件查询会员总数
	 * @param t
	 * @return
	 */
	public int total(OrderBean t);
	
}
