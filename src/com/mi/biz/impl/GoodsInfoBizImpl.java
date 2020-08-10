package com.mi.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mi.biz.GoodsInfoBiz;
import com.mi.dao.GoodsInfoDao;
import com.mi.dao.GoodsTypeDao;
import com.mi.dao.impl.GoodsInfoDaoImpl;
import com.mi.dao.impl.GoodsTypeDaoImpl;
import com.mi.entity.GoodsInfo;
import com.mi.util.StringUtil;

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

	@Override
	public int add(Map<String, String> map) {
		if (StringUtil.checkNull(map.get("pname"),map.get("tno"),map.get("status"),map.get("balance"),map.get("price"))) {
			return -1;
		}
		GoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return goodsInfoDao.add(map);
	}

	@Override
	public List<GoodsInfo> findAll() {
		GoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		return goodsInfoDao.findAll();
	}

	@Override
	public Map<String, Object> findIndex() {
		GoodsTypeDao typeDao  = new GoodsTypeDaoImpl();
		GoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("types", typeDao.findAll());
		map.put("goods", goodsInfoDao.findIndex());
		return map;
	}

}
