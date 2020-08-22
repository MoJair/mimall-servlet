package com.mi.biz.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.mi.biz.ICartInfoBiz;
import com.mi.dao.ICartInfoDao;
import com.mi.dao.impl.CartInfoDaoImpl;
import com.mi.entity.CartInfo;
import com.mi.util.StringUtil;

public class CartInfoBizImpl implements ICartInfoBiz{

	@Override
	public List<Map<String, Object>> findCart(String mno) {
		if (StringUtil.checkNull(mno)) {
			return Collections.emptyList();
		}
		ICartInfoDao cartInfoDao = new CartInfoDaoImpl();
		return cartInfoDao.findCart(mno);
	}

	@Override
	public List<CartInfo> finds(String mno) {
		if (StringUtil.checkNull(mno)) {
			return Collections.emptyList();
		}
		ICartInfoDao cartInfoDao = new CartInfoDaoImpl();
		return cartInfoDao.finds(mno);
	}

	@Override
	public int updateNum(String cno, int num) {
		if (StringUtil.checkNull(cno)) {
			return -1;
		}
		ICartInfoDao cartInfoDao = new CartInfoDaoImpl();
		return cartInfoDao.updateNum(cno, num);
	}

	@Override
	public int add(CartInfo cf) {
		if (StringUtil.checkNull(cf.getCno())) {
			return -1;
		}
		ICartInfoDao cartInfoDao = new CartInfoDaoImpl();
		return cartInfoDao.add(cf);
	}

	@Override
	public int delete(String cnos) {
		if (StringUtil.checkNull(cnos)) {
			return -1;
		}
		ICartInfoDao cartInfoDao = new CartInfoDaoImpl();
		if (cnos.contains(",")) {
			return cartInfoDao.delete(cnos.split(","));
		}
		return cartInfoDao.delete(cnos);
	}

	@Override
	public List<CartInfo> findByCnos(String cnos) {
		if (StringUtil.checkNull(cnos)) {
			return Collections.emptyList();
		}
		ICartInfoDao cartInfoDao = new CartInfoDaoImpl();
		String[] temp = cnos.split(",");
		return cartInfoDao.findByCnos(temp);
	}


}
