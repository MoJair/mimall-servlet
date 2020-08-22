package com.mi.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.mi.entity.OrderBean;
import com.mi.util.StringUtil;

public class OrderDAO {

	DBHelper db = new DBHelper();

	/**
	 * 添加订单
	 * 
	 * @param cnos       购物车编号
	 * @param ano        收货地址编号
	 * @param totalPrice 总金额
	 * @return 返回 订单编号
	 * @throws SQLException
	 */
	public String add(String cnos, String ano, String totalPrice) throws SQLException {
		// 生成订单编号 订单表 添加 订单编号 ono 时间戳 + 随机数
		String ono = new Date().getTime() + "" + new Random().nextInt(10000);
		// sqls params
		List<String> sqls = new ArrayList<String>();
		List<List<Object>> params = new ArrayList<List<Object>>();

		// 1.添加订单 订单编号 下单时间 收货地址编号 订单总额 待支付
		String sql1 = "insert into orderinfo values(?,now(),?,null,null,1,?,0)";
		List<Object> param1 = new ArrayList<Object>();
		Collections.addAll(param1, ono, ano, totalPrice);
		
		// 2.添加订单详情 订单详情编号 订单编号 商品编号 数量 价格 状态
		String sql2 = "insert into orderiteminfo select 0,?,cf.pid,num,price from cartinfo cf,productinfo cp where cf.pid = cp.pid and(cp.pid=? or cno in (";
		List<Object> param2 = new ArrayList<Object>();
		String[] arrs = cnos.split(",");
		param2.add(ono);// 存入订单编号
		if(arrs[0].length()>7) {//数据库int值判断超过某范围也会报错
			param2.add(0);
		}else {
			param2.add(arrs[0]);
		}
		
		for (String cno : arrs) {
			sql2 += "?, ";
			param2.add(cno);
		}
		sql2 = sql2.substring(0, sql2.lastIndexOf(",")) + "))";

		// 3，修改商品库存
		String sql3 = "update productinfo set balance = balance-case ";
		List<Object> param3 = new ArrayList<Object>();
		for (String cno : arrs) {
			sql3 += " when pid = (select pid from cartinfo where cno = ?) then (select num from cartinfo where cno = ?)";
			param3.add(cno);
			param3.add(cno);
		}
		sql3 += " end where pid in (select pid from cartinfo where cno in (";

		// 4.删除对应商品所在的购物车
		String sql4 = "delete from cartinfo where cno in (";
		List<Object> param4 = new ArrayList<Object>();
		for (String cno : arrs) {
			sql3 += "?, ";
			sql4 += "?, ";
			param3.add(cno);
			param4.add(cno);
		}

		sql3 = sql3.substring(0, sql3.lastIndexOf(",")) + "))";
		sql4 = sql4.substring(0, sql4.lastIndexOf(",")) + ")";

		// 存入所有的sql语句和参数
		Collections.addAll(sqls, sql1, sql2, sql3, sql4);
		Collections.addAll(params, param1, param2, param3, param4);

		// 执行sql
		if (db.update(sqls, params) > 0) {
			return ono;// 订单编号
		}

		return null;
	}

	/**
	 * 根据会员编号查询订单
	 * 
	 * @param mno
	 * @return
	 */
	public List<Map<String, String>> findByUid(int uid) {
		String sql = "select o.ono, date_format(odate, '%Y-%m-%d %H:%i') odate, o.status, o.price  amount, i.pid, i.nums, i.price, pname, pics, intro, color "
				+ " from orderinfo o, orderiteminfo i, productinfo cp, addrinfo a where o.ono=i.ono and i.pid=cp.pid and o.ano=a.ano and a.uid=? order by o.ono desc";
		System.out.println(db.findss(sql,uid));
		return db.findss(sql,uid);
	}

	/**
	 * 删除订单
	 * 
	 * @param ono
	 * @return
	 * @throws SQLException
	 */
	/*
	 * public int delete(String ono) throws SQLException { List<String> sqls = new
	 * ArrayList<String>(); List<List<Object>> params = new
	 * ArrayList<List<Object>>();
	 * 
	 * // SET foreign_key_checks = 0; -- 先设置外键约束检查关闭 String sql1 =
	 * "SET foreign_key_checks = ?"; List<Object> param1 = new ArrayList<Object>();
	 * param1.add(0);
	 * 
	 * // 删除数据 String sql2 =
	 * "delete orderInfo,orderItemInfo from orderInfo left join orderItemInfo on orderInfo.ono=orderItemInfo.ono where orderInfo.ono = ?"
	 * ; List<Object> param2 = new ArrayList<Object>(); param2.add(ono);
	 * 
	 * // SET foreign_key_checks = 1; -- 开启外键约束检查，以保持表结构完整性 String sql3 =
	 * "SET foreign_key_checks = ?"; List<Object> param3 = new ArrayList<Object>();
	 * param3.add(1);
	 * 
	 * // 存入所有的sql语句和参数 Collections.addAll(sqls, sql1, sql2, sql3);
	 * Collections.addAll(params, param1, param2, param3); return db.update(sqls,
	 * params); }
	 */
	public int delete(String ono) {
		String sql = "delete from orderInfo where ono = ?";
		int result = delete1(ono);
		System.out.println(result);
		if( result > 0 ) {
			return db.update(sql, ono);
		}
		return 0;
	}

	private int delete1(String ono) {
		String sql = "delete from orderItemInfo where ono = ?";
		return db.update(sql, ono);
	}
	/**
	 * 根据会员编号查询订单
	 * 
	 * @param mno
	 * @return
	 */
	public List<Map<String, String>> findByUid1(int uid) {
		String sql = "select o.ono,a.uid,i.ino, date_format(odate, '%Y-%m-%d %H:%i') odate, o.status, o.price amount, i.pid, i.nums, i.price, cname, pics, ctype, color "
				+ " from orderinfo o, orderiteminfo i, productinfo cp, addrinfo a where o.ono=i.ono and i.pid=cp.pid and o.ano=a.ano and a.uid=? order by o.ono desc";
		return db.findss(sql, uid);
	}
	/**
	 * 修改状态
	 * @param t
	 * @return
	 */
	public int update(OrderBean t) {
		String sql = "update orderInfo  set status = ? where ono = ? ";
		//System.out.println(db.update(sql, t.getStatus(),t.getAno()));
	    return db.update(sql, t.getStatus(),t.getOno());
		
		
	}
	/**
	 * 查询所有订单信息和条件查询
	 */
	public List<Map<String, String>> findByTrem(String ono,String status,int page, int rows){
		String sql="select o.ono, date_format(odate, '%Y-%m-%d %H:%i') odate, o.status, o.price amount, a.name, i.nums, cname, color from orderinfo o, orderiteminfo i, productinfo cp, addrinfo a "
				+ "where o.ono=i.ono and i.pid=cp.pid and o.ano=a.ano ";
		if( ono == "" ) {//防止传入"",而导致查询不出数据
			ono = null;
		}
		if( status == "" ) {
			status = null;
		}
		if( ono != null && status == null) {//通过订单编号查询
			sql+=" and o.ono = ? ";
			sql+=" order by o.ono desc limit ?,?";
			return db.findss(sql, ono,(page - 1) * rows,rows);
		}else if(ono == null && status != null){//通过状态查询
			sql += " and o.status=? ";
			sql+=" order by o.ono desc limit ?,?";
			return db.findss(sql, status,(page - 1) * rows,rows);
		}else if(ono != null && status != null){//通过ono和status连接查询
			sql+=" and o.ono = ? and o.status=? ";
			sql+=" order by o.ono desc limit ?,?";
			return db.findss(sql, ono,status,(page - 1) * rows,rows);
		}else {//查询所有
			sql+=" order by o.ono desc limit ?,?";
			return db.findss(sql, (page - 1) * rows,rows);
		}
	}
	
	/**
	 * 条件查询会员总数
	 * @param t
	 * @return
	 */
	public int total(OrderBean t) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(ono) from orderinfo where 1=1 ");
		List<Object> params = null;
		if( t != null ) {
			params = new ArrayList<Object>();
			//通过订单号
			if( !StringUtil.checkNull(t.getOno()) ) {
				sb.append(" and ono = ? ");
				params.add(t.getOno());
			}
			if( t.getStatus() != 0 ) {//订单状态
				sb.append(" and status = ? ");
				params.add(t.getStatus());
			}
			//sb.append(" order by mno desc ");
		}
		return db.total(sb.toString(), params);
	}
}
