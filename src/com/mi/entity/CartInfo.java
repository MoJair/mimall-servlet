package com.mi.entity;

import java.io.Serializable;

public class CartInfo implements Serializable{
	private static final long serialVersionUID = 246571561931057947L;

	private String cno;
	private Integer mno;
	private Integer gno;
	private Integer num;
	private String gname; //商品名称
	private double price; //价格
	private String pics; //图片
	private String unit; //单位
	private String weight; //净重
	
	@Override
	public String toString() {
		return "CartInfo [cno=" + cno + ", mno=" + mno + ", gno=" + gno + ", num=" + num + ", "
				+ "gname=" + gname + ", price=" + price + ", pics=" + pics + ", unit=" + unit + ","
						+ " weight=" + weight + "]";
	}
	
	public String getCno() {
		return cno;
	}
	
	public void setCno(String cno) {
		this.cno = cno;
	}
	
	public Integer getMno() {
		return mno;
	}
	
	public void setMno(Integer mno) {
		this.mno = mno;
	}
	
	public Integer getGno() {
		return gno;
	}
	
	public void setGno(Integer gno) {
		this.gno = gno;
	}
	
	public Integer getNum() {
		return num;
	}
	
	public void setNum(Integer num) {
		this.num = num;
	}
	
	public String getGname() {
		return gname;
	}
	
	public void setGname(String gname) {
		this.gname = gname;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getPics() {
		return pics;
	}
	
	public void setPics(String pics) {
		this.pics = pics;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getWeight() {
		return weight;
	}
	
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
}
