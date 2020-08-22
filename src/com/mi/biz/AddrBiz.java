package com.mi.biz;

import java.util.List;

import com.mi.entity.Addrbean;


public interface AddrBiz {

	/**
	 * 添加地址
	 * @param t
	 * @return
	 */
	public int add(Addrbean t) ;
	
	
	/**
	 * 条件查询用户地址信息
	 * @param t
	 * @return
	 */
	public List<Addrbean> findByTrem(Addrbean t);
	
	
	
	/**
	 *修改状态
	 */
	public int delete(String ano) ;
	
	public int update(Addrbean t) ;
	
	/**
	 * 添加地址
	 * @param t
	 * @return
	 */
	public int add2(Addrbean t) ;
	


	public Object findByUid2(int uid);

}
