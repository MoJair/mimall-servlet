package com.mi.biz;

import java.util.List;

import com.mi.entity.Admin;


public interface IAdminBiz {
    public Admin find(String aname,String pwd);
    public List<Admin> finds();
    public int update(Admin ad);
}
