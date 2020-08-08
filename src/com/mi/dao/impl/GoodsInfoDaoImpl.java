package com.mi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.mi.dao.DBHelper;
import com.mi.dao.GoodsInfoDao;
import com.mi.entity.GoodsInfo;
import com.mi.util.StringUtil;

public class GoodsInfoDaoImpl implements GoodsInfoDao{

	@Override
	public List<GoodsInfo> findByPage(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select pid, pname, p.tno, version, color, price, balance, intro from productInfo p, types t "
				+ "where p.tno=t.tno order by pid desc limit ?, ?";
		return db.finds(GoodsInfo.class, sql, (page - 1) * rows, rows);
	}

	@Override
	public int total() {
		DBHelper db = new DBHelper();
		String sql = "select count(pid) from productInfo";
		return db.total(sql);
	}

	@Override
	public int total(String tno, String tname) {
		DBHelper db = new DBHelper();
		String sql = "select count(pid) from productInfo where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.checkNull(tno)) {
			sql += " and tno = ?";
			params.add(tno);
		}
		if (!StringUtil.checkNull(tname)) {
			sql += " and pname like concat('%', ?, '%')";
			params.add(tname);
		}
		return db.total(sql, params);
	}

	@Override
	public List<GoodsInfo> findByCondition(String tno, String pname, int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select pid, pname, p.tno, version, color, price, balance, intro from productInfo p, types t "
				+ "where p.tno=t.tno ";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.checkNull(tno)) {
			sql += " and p.tno = ?";
			params.add(tno);
		}
		if (!StringUtil.checkNull(pname)) {
			sql += " and pname like concat('%', ?, '%')";
			params.add(pname);
		}
		sql += " order by pid desc limit ?, ?";
		params.add( (page - 1) * rows );
		params.add( rows );
		return db.finds(GoodsInfo.class, sql, params);
	}

}
