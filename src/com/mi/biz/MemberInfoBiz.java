package com.mi.biz;

import com.mi.entity.MemberInfo;

public interface MemberInfoBiz {
	public MemberInfo login(String account, String pwd);
	
	public int register(String uname, String pwd, String tel);

}
