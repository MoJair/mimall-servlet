package com.mi.biz.impl;

import java.util.List;

import com.mi.biz.SelfInfoBiz;
import com.mi.dao.SelfInfoDao;
import com.mi.dao.impl.SelfInfoDaoImpl;
import com.mi.entity.AddrInfo;
import com.mi.entity.MemberInfo;

public class SelfInfoBizImpl implements SelfInfoBiz{

	@Override
	public MemberInfo findByUid(String uid) {
		SelfInfoDao selfInfoDao = new SelfInfoDaoImpl();
		return selfInfoDao.findByUid(uid);
	}

	@Override
	public int update(MemberInfo type) {
		SelfInfoDao selfInfoDao = new SelfInfoDaoImpl();
		return selfInfoDao.update(type);
	}

	@Override
	public AddrInfo findAddrByUid(String uid) {
		SelfInfoDao selfInfoDao = new SelfInfoDaoImpl();
		return selfInfoDao.findAddrByUid(uid);
	}

	@Override
	public int updateaddr(AddrInfo addr) {
		SelfInfoDao selfInfoDao = new SelfInfoDaoImpl();
		return selfInfoDao.updateaddr(addr);
	}

	@Override
	public List<MemberInfo> findAll() {
		SelfInfoDao selfInfoDao = new SelfInfoDaoImpl();
		return selfInfoDao.findAll();
	}

}
