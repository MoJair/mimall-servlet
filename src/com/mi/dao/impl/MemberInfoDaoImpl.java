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
	

}
