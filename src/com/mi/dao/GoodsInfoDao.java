package com.mi.dao;

import java.util.List;

import com.mi.entity.GoodsInfo;

public interface GoodsInfoDao {
	public List<GoodsInfo> findByPage(int page , int rows);
	public int total();
	public int total(String tno, String pname);
	public List<GoodsInfo> findByCondition(String tno, String pname, int page, int rows);
	
	public List<GoodsInfo> findIndex();
	
}
