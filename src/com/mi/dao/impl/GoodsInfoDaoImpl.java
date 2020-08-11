package com.mi.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mi.dao.DBHelper;
import com.mi.dao.GoodsInfoDao;
import com.mi.entity.GoodsInfo;
import com.mi.util.StringUtil;

public class GoodsInfoDaoImpl implements GoodsInfoDao{

	@Override
	public List<GoodsInfo> findByPage(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select pid,type, pname, p.tno, version, color,p.status, price, balance,pics ,intro from productinfo p, types t "
				+ "where p.tno=t.tno order by pid asc limit ?, ?";
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
		String sql = "select pid, pname, p.tno,pics, version, color, price, balance, intro from productInfo p, types t "
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

	public int add(Map<String, String> map) {
		DBHelper db = new DBHelper();
		String sql = "insert into  productInfo values(0,?,?,?,?,?,?,?,?,?,?,?)";
		return db.update(sql, map.get("pname"),map.get("tno"),map.get("size"),map.get("version"),map.get("color"),map.get("price"),map.get("intro"),map.get("type"),map.get("balance"),map.get("pics"),map.get("status"));
	}

	@Override
	public List<GoodsInfo> findAll() {
		DBHelper db = new DBHelper();
		String sql = "select pid, pname, tno, version, color, price, balance,pics,type,intro from productInfo where status!=0";
		return db.finds(GoodsInfo.class, sql);
	}

	@Override
	public List<GoodsInfo> findIndex() {
			DBHelper db =new DBHelper();
			String sql ="select pid,pname,price,tno,intro,pics,type from productinfo g1 where 8 > (select count(pid) from productinfo g2 where g1.tno = g2.tno and g1.pid < g2.pid ) order by g1.tno asc, g1.pid desc";
			return db.finds(GoodsInfo.class, sql);
	}

	@Override
	public List<GoodsInfo> findByPname(String tno, String pname) {
		DBHelper db = new DBHelper();
		String sql = "select pid, pname, p.tno,pics, version, color, price, balance, intro from productInfo p, types t "
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
		return db.finds(GoodsInfo.class, sql, params);
	}

}
