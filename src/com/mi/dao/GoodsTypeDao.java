package com.mi.dao;

import java.util.List;

import com.mi.entity.GoodsType;


public interface GoodsTypeDao {
public List<GoodsType> findAll();
	
	public int update(GoodsType type);
	
	public int add(GoodsType type);
	
	public int delete(String tno);

}
