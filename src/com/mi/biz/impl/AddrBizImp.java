package com.mi.biz.impl;

import java.util.List;

import com.mi.biz.AddrBiz;
import com.mi.dao.AddrDAO;
import com.mi.entity.Addrbean;


public class AddrBizImp implements AddrBiz {

	AddrDAO dao = new AddrDAO();
	Addrbean t = new Addrbean();
	/**
	 * 添加地址
	 * @param t
	 * @return
	 */
	@Override
	public int add(Addrbean t) {
		return dao.add(t);
	}

	
	/**
	 * 条件查询用户地址信息
	 * @param t
	 * @return
	 */
	@Override
	public List<Addrbean> findByTrem(Addrbean t) {
		return dao.findByTrem(t);
	}

	

	/**
	 *修改状态
	 */
	@Override
	public int delete(String ano) {

		t.setAno(ano);
		
		return dao.delete(t);
	}


	@Override
	public int update(Addrbean t) {
		return dao.update(t);
	}


	@Override
	public int add2(Addrbean t) {
		return dao.add2(t);
	}


	@Override
	public Object findByUid2(int uid) {
		return dao.findByTrem2(uid);
	}


}
