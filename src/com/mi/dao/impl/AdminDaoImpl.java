package com.mi.dao.impl;

import java.util.List;

import com.mi.dao.DBHelper;
import com.mi.dao.IAdminDao;
import com.mi.entity.Admin;



public class AdminDaoImpl  implements IAdminDao{

	@Override
	public Admin find(String aname, String pwd) {
		DBHelper db=new DBHelper();
		String sql="select  aname ,status from admininfo where aname=? and pwd=? and status!=2";
	 return db.find(Admin.class, sql, aname,pwd);
	}
	public List<Admin> finds() {
		DBHelper db=new DBHelper();
		String sql="select aid,aname,pwd ,status from admininfo where  status!=0";
	  return db.finds(Admin.class, sql);
	}

	@Override
	public int updateName(Admin ad) {
		DBHelper db=new DBHelper();
		String sql="update admininfo set status=?,pwd=md5(?) where aid=?";
		return db.update(sql, ad.getStatus(),ad.getPwd(),ad.getAid());
	}

	@Override
	public int updatePwd(Admin ad) {
		DBHelper db=new DBHelper();
		String sql="update admininfo set pwd=? where aid=?";
		return db.update(sql, ad.getPwd(),ad.getAid());
	}

	

}
