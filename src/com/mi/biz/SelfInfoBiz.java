package com.mi.biz;

import java.util.List;

import com.mi.entity.AddrInfo;
import com.mi.entity.MemberInfo;

public interface SelfInfoBiz {
	public MemberInfo findByUid(String uid);
	
	public int update(MemberInfo type);
	
	public AddrInfo findAddrByUid(String uid);
	
	public int updateaddr(AddrInfo addr);
	
	public List<MemberInfo> findAll();
}
