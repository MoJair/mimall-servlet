package com.mi.dao.impl;


import java.util.List;

import com.mi.dao.DBHelper;
import com.mi.dao.SelfInfoDao;
import com.mi.entity.AddrInfo;
import com.mi.entity.MemberInfo;

public class SelfInfoDaoImpl implements SelfInfoDao{
	
	@Override
	public MemberInfo findByUid(String uid) {
		DBHelper db = new DBHelper();
		String sql = "select uname, pwd, sex, tel, email, hobby from userInfo where status!=0 and uid=?";
		System.out.println("123" + uid);
		return db.find(MemberInfo.class, sql, uid);
	}

	@Override
	public int update(MemberInfo type) {
		DBHelper db = new DBHelper();
		String sql = "update userInfo set uname=?, pwd=?, sex=?, tel=?, email=?, hobby=? where uid=?";
		return db.update(sql, type.getUname(), type.getPwd(), type.getSex(), type.getTel(), type.getEmail(), type.getHobby(), type.getUid());
	}

	@Override
	public AddrInfo findAddrByUid(String uid) {
		DBHelper db = new DBHelper();
		String sql = "select uid, name, postcode, province, city, area, addr, tel from addrInfo where status!=0 and uid=?";
		return db.find(AddrInfo.class, sql, uid);
	}

	@Override
	public int updateaddr(AddrInfo addr) {
		DBHelper db = new DBHelper();
		String sql = "update addrInfo set name=?, postcode=?, province=?, city=?, area=?, addr=?, tel=? where uid=?";
		return db.update(sql, addr.getName(), addr.getPostcode(), addr.getProvince(), addr.getCity(), addr.getArea(), addr.getAddr(), addr.getTel(), addr.getUid());
	}

	@Override
	public List<MemberInfo> findAll() {
		DBHelper db = new DBHelper();
		String sql = "select uid, uname, pwd, sex, tel, email, hobby, status from userInfo"; //userInfo用户信息表
		return db.finds(MemberInfo.class, sql);
	}


	
}
