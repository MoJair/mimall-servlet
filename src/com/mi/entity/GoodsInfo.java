package com.mi.entity;

import java.io.Serializable;

import com.mi.util.StringUtil;


public class GoodsInfo implements Serializable{
	private static final long serialVersionUID = 8286695774577824242L;

	private Integer pid; //gno
	private String pname;	//名称
	private Integer tno;	//外键 类型表
	private String size;	//尺寸
	private String version;	//产品版本
	private String color;	//颜色
	private String price;  //单价 double
	private String intro;  //简介
	private String type;	//型号
	private Integer balance;	//库存
	private String pics;  //图片
	private String status;	//状态
	
	@Override
	public String toString() {
		return "productInfo [pid=" + pid + ", pname=" + pname + ", tno=" + tno + ", size=" + size + ", "
				+ "version=" + version + ", color=" + color + ", price=" + price + ", intro=" + intro + ","
						+ " type=" + type + ", balance=" + balance + ", pics=" + pics + ", status=" + status +"]";
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getTno() {
		return tno;
	}

	public void setTno(Integer tno) {
		this.tno = tno;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
	
	
	