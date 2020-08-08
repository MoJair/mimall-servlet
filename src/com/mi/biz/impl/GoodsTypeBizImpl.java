package com.mi.biz.impl;

import java.util.List;
import com.mi.biz.GoodsTypeBiz;
import com.mi.dao.GoodsTypeDao;
import com.mi.dao.impl.GoodsTypeDaoImpl;
import com.mi.entity.GoodsType;
import com.mi.util.StringUtil;


public class GoodsTypeBizImpl implements GoodsTypeBiz{

	@Override
	public List<GoodsType> findAll() {
		GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
		return goodsTypeDao.findAll();
	}

	@Override
	public int update(GoodsType type) {
		if(StringUtil.checkNull(type.getTname())) {
			return -1;
		}
		GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
		return goodsTypeDao.update(type);
	}

	@Override
	public int add(GoodsType type) {
		if(StringUtil.checkNull(type.getTname())) {
			return -1;
		}
		GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
		return goodsTypeDao.add(type);
	}

	@Override
	public int delete(String tno) {
		if(StringUtil.checkNull(tno)) {
			return -1;
		}
		GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
		return goodsTypeDao.delete(tno);
	}

}
