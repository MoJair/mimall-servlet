package com.mi.dao;

import java.util.List;

import com.mi.entity.Admin;


public interface IAdminDao {
	/*
	 * 实现登录
	 */
    public Admin find(String aname,String pwd);
    /*
     * 冻结 解锁账号
     */
    public int updateName(Admin ad);
    /*
     * 重置密码
     */
    public int updatePwd(Admin ad);
    /*
     * 查询所有管理员
     */
    public List<Admin> finds();
}
