package com.mi.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mi.biz.GoodsInfoBiz;
import com.mi.dao.GoodsInfoDao;
import com.mi.dao.impl.GoodsInfoDaoImpl;
import com.mi.entity.GoodsInfo;

public class GoodsInfoBizImpl implements GoodsInfoBiz{

	@Override
	public List<GoodsInfo> findByPage(int page, int rows) {
		GoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return goodsInfoDao.findByPage(page, rows);
	}

	@Override
	public Map<String, Object> finds(int page, int rows) {
		GoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", goodsInfoDao.total());
		map.put("rows", goodsInfoDao.findByPage (page, rows));
		return map;
	}

	@Override
	public Map<String, Object> findByCondition(String tno, String pname, int page, int rows) {
		GoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", goodsInfoDao.total(tno, pname));
		map.put("rows", goodsInfoDao.findByCondition (tno, pname, page, rows));
		return map;
	}

}