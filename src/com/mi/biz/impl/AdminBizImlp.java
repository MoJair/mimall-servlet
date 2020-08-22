package com.mi.biz.impl;

import java.util.List;

import com.mi.biz.IAdminBiz;
import com.mi.dao.IAdminDao;
import com.mi.dao.impl.AdminDaoImpl;
import com.mi.entity.Admin;
import com.mi.util.StringUtil;


public class AdminBizImlp implements IAdminBiz{

	@Override
	public Admin find(String aname, String pwd) {
	
	if(StringUtil.checkNull(aname,pwd)) {
		return null ;
	}
	IAdminDao adminDao=new AdminDaoImpl();
	return adminDao.find(aname, pwd);
	}

	@Override
	public int update(Admin ad) {
		if(StringUtil.checkNull(String.valueOf(ad.getAid()))) {
			return -1;
		}
		IAdminDao adminDao=new AdminDaoImpl();
		
		return adminDao.updateName(ad);
	}

	@Override
	public List<Admin> finds() {
		IAdminDao newDao=new AdminDaoImpl();
		return  newDao.finds();
	}

}
