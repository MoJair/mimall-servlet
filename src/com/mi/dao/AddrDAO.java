package com.mi.dao;

import java.util.ArrayList;
import java.util.List;

import com.mi.entity.Addrbean;
import com.mi.util.StringUtil;


public class AddrDAO  implements BaseDAO<Addrbean>{

	DBHelper db= new DBHelper();
	/**
	 *添加地址
	 */
	@Override
	public int add(Addrbean t) {
		String sql = "insert into addrInfo values(?,?,?,?,?,?,?,?,?,?,0,1)";
		return db.update(sql, t.getAno(),t.getUid(),t.getName(),t.getPostcode(),t.getProvince()
				,t.getCity(),t.getArea(),t.getAddr(),t.getTel(),t.getFixedtel());
	}

	/**
	 *条件查询用户地址信息
	 */
	@Override
	public List<Addrbean> findByTrem(Addrbean t) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("select ano,uid,name,postcode,province,city,area,addr,tel,fixedtel,flag,status from addrInfo where 1=1");
		List<Object> params= new ArrayList<Object>();
		if(t !=null) {
			
			//通过昵称  模糊查询
			if(!StringUtil.checkNull(t.getName())) {
				sb.append(" and name like concat('%',?,'%') ");
				params.add(t.getName());
			}
			if (t.getUid() != 0) {
				System.out.println(t.getUid());
				sb.append(" and uid = ? ");
				params.add(t.getUid());
				
			}
		}
		sb.append(" order by ano desc ");
		//System.out.println(sb.toString());
		return db.findMutiple(sb.toString(), params,Addrbean.class);
	}
//
//	@Override
//	public int update(Addrbean t) {
//		StringBuffer sb = new StringBuffer();
//		sb.append("update addrInfo set status = 1 ");
//		
//		List<Object> params = new ArrayList<Object>();
//		if( t != null) {
//			if(!StringUtil.isNull(t.getName())) {
//				sb.append(" ,name = ?");
//				params.add(t.getName());
//			}
//			if(!StringUtil.isNull(t.getPostcode())) {
//				sb.append(" ,postcode = ?");
//				params.add(t.getPostcode());
//			}
//			if(!StringUtil.isNull(t.getProvince())) {
//				sb.append(" ,province = ?");
//				params.add(t.getProvince());
//			}
//			if(!StringUtil.isNull(t.getCity())) {
//				sb.append(" ,city = ?");
//				params.add(t.getCity());
//			}
//			if(!StringUtil.isNull(t.getArea())) {
//				sb.append(" ,area = ?");
//				params.add(t.getArea());
//			}
//			if(!StringUtil.isNull(t.getAddr())) {
//				sb.append(" ,addr = ?");
//				params.add(t.getAddr());
//			}if(!StringUtil.isNull(t.getTel())) {
//				sb.append(" ,tel = ?");
//				params.add(t.getTel());
//			}if(!StringUtil.isNull(t.getAno())) {
//				sb.append(" where ano = ? ");
//				params.add(t.getAno());
//			}
//			
//			
//		}
//		System.out.println(params);
//		return db.update(sb.toString(), params);
//	}
//	
	@Override
	public int update(Addrbean t) {
		
		String sql ="update addrInfo set name = ?  ,postcode = ?,province = ?,addr = ?,tel = ? ,city = ? ,area = ? where ano = ? ";
		
		
		return db.update(sql, t.getName(),t.getPostcode(),t.getProvince(),t.getAddr(),t.getTel(),t.getCity(),t.getArea(),t.getAno());
	}

	/**
	 *修改状态
	 */
	@Override
	public int delete(Addrbean t) {
		String sql = " delete from  addrInfo where ano = ?";
		return db.update(sql, t.getAno());
	}

	public int add2(Addrbean t) {
		String sql = "insert into addrInfo values(?,?,?,?,?,?,?,?,?,?,0,0)";
		return db.update(sql, t.getAno(),t.getUid(),t.getName(),t.getPostcode(),t.getProvince()
		,t.getCity(),t.getArea(),t.getAddr(),t.getTel(),t.getFixedtel());
	}

	public List<Addrbean> findByTrem2(int uid) {
		String sql = "select * from addrInfo where uid = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(uid);
		return db.findMutiple(sql, params, Addrbean.class);
	}

}
