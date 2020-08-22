package com.mi.dao;

import java.util.List;
import java.util.Map;

import com.mi.entity.CartInfo;

public interface CartInfoDao {
	/**
	 * 只查购物车编号和商品编号
	 * @param pid
	 * @return
	 */
	public List<Map<String, Object>> findCart(String pid);
	
	/**
	 * 根据会员编号，查询购物车详细信息
	 * @param pid
	 * @return
	 */
	public List<CartInfo> finds(String pid);
	
	/**
	 * 修改数量
	 * @param cno
	 * @param num
	 * @return
	 */
	public int updateNum(String cno, int num);
	
	/**
	 * 添加购物车
	 * @param cf
	 * @return
	 */
	public int add(CartInfo cf);
	
	/**
	 * 删除购物车
	 * @param cnos
	 * @return
	 */
	public int delete(String cno);
	
	public int delete (String[] cnos);
	
	

	
	

}
