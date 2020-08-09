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

}
