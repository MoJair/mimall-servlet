package com.mi.biz;

import java.util.List;
import java.util.Map;

import com.mi.entity.GoodsInfo;

public interface GoodsInfoBiz {
	public List<GoodsInfo> findByPage(int page, int rows);
	
	public Map<String, Object> finds(int page, int rows);

	public Map<String, Object> findByCondition(String tno, String pname, int page, int rows);

}
