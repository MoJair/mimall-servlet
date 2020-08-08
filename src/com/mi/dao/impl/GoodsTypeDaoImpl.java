package com.mi.dao.impl;

import java.util.List;

import com.mi.dao.DBHelper;
import com.mi.dao.GoodsTypeDao;
import com.mi.entity.GoodsType;

public class GoodsTypeDaoImpl implements GoodsTypeDao{

	@Override
	public List<GoodsType> findAll() {
		DBHelper db = new DBHelper();
		String sql = "select tno, tname, status from types"; //types商品类型表
		return db.finds(GoodsType.class, sql);
	}

	@Override
	public int update(GoodsType type) {
		DBHelper db = new DBHelper();
		String sql = "update types set tname=?, status=? where tno=?";
		return db.update(sql, type.getTname(), type.getStatus(), type.getTno());
	}

	@Override
	public int add(GoodsType type) {
		DBHelper db = new DBHelper();
		String sql = "insert into types values(0, ?, ?)";
		return db.update(sql, type.getTname(), type.getStatus());
	}

	@Override
	public int delete(String tno) {
		DBHelper db = new DBHelper();
		String sql = "delete from types where tno=?";
		return db.update(sql, tno);
	}

}
