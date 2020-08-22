package com.mi.dao;

import java.util.List;
import java.util.Map;

import com.mi.entity.CartInfo;


public interface ICartInfoDao {
	/**
	 * 只查购物车和商品编号
	 * @param mno
	 * @return
	 */
	public List<Map<String, Object>> findCart(String mno);
	/**
	 * 根据会员编号查所有
	 * @param mno
	 * @return
	 */
	public List<CartInfo> finds(String mno);
	/**
	 * 修改数量
	 * @param con
	 * @param num
	 * @return
	 */
	public int updateNum(String cno,int num);
	/**
	 * 添加
	 * @param cf
	 * @return
	 */
	public int add(CartInfo cf);
	
	/**
	 * 删除
	 * @param cons
	 * @return
	 */
	public int delete(String[] cnos);
	public int delete(String cno);
	public List<CartInfo> findByCnos(String[] cnos);
	
}
