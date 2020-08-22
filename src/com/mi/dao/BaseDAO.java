package com.mi.dao;

import java.util.List;

/**
 * 数据库基本操作接口
 * @author 38929
 *
 */
public interface BaseDAO<T> {

	/**
	* 添加操作
	* @param t
	* @return
	*/
	public int add(T t);
	
	/**
	★根据条件查询
	* @param t
	* @return
	*/
	public List<T>findByTrem(T t); 
	
	/**
	*修改操作
	* @param t
	* @return
	*/
	public int update (T t);
	
	/**删除操作 物理删除  逻辑删除
	* @param t
	* @return
	*/
	public int delete (T t);

}
