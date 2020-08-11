package com.mi.biz.impl;

import com.mi.biz.MemberInfoBiz;
import com.mi.dao.MemberInfoDao;
import com.mi.dao.impl.MemberInfoDaoImpl;
import com.mi.entity.MemberInfo;
import com.mi.util.StringUtil;

public class MemberInfoBizImpl implements MemberInfoBiz{

	@Override
	public MemberInfo login(String account, String pwd) {
		if (StringUtil.checkNull(account, pwd)) {
			return null;
		}
		MemberInfoDao memberInfoDao = new MemberInfoDaoImpl();
		return memberInfoDao.login(account, pwd);
	}

	@Override
	public int register(String uname, String pwd, String tel) {
		if (StringUtil.checkNull(uname, pwd, tel)) {
			return -1;
		}
		MemberInfoDao memberInfoDao = new MemberInfoDaoImpl();
		return memberInfoDao.register(uname, pwd, tel);
	}

}
