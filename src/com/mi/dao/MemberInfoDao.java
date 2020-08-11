package com.mi.dao;

import com.mi.entity.MemberInfo;

public interface MemberInfoDao {
	public MemberInfo login(String account, String pwd);
	
	public int register(String uname, String pwd, String tel);

}
