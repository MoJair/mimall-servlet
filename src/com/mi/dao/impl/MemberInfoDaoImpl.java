package com.mi.dao.impl;

import com.mi.dao.DBHelper;
import com.mi.dao.MemberInfoDao;
import com.mi.entity.MemberInfo;

public class MemberInfoDaoImpl implements MemberInfoDao{

	@Override
	public MemberInfo login(String account, String pwd) {
		DBHelper db = new DBHelper();
		String sql = "select uid, uname, tel, email from userInfo where (uname=? or tel=? or email=?) and pwd=?";
		return db.find(MemberInfo.class, sql, account, account, account, pwd);
	}

	@Override
	public int register(String uname, String pwd, String tel) {
		DBHelper db = new DBHelper();
		String sql = "insert into userInfo values (0,?,?,'男', ?,null,null,'1')";
		return db.update(sql, uname, pwd, tel);
	}
	

}
