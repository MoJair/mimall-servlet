package com.mi.entity;

import java.io.Serializable;

public class OrderItemInfo implements Serializable{
	private static final long serialVersionUID = 6705137857967163264L;

	private Integer ino;
	private String ono; //订单编号
	private Integer gno; //商品编号
	private Integer nums; //购买数量
	private double price; //购买单价
	private Integer status;
	
	
	@Override
	public String toString() {
		return "OrderItemInfo [ino=" + ino + ", ono=" + ono + ", gno=" + gno + ", nums=" + nums + ", "
				+ "price=" + price + ", status=" + status + "]";
	}
	
	public Integer getIno() {
		return ino;
	}
	
	public void setIno(Integer ino) {
		this.ino = ino;
	}
	
	public String getOno() {
		return ono;
	}
	
	public void setOno(String ono) {
		this.ono = ono;
	}
	
	public Integer getGno() {
		return gno;
	}
	
	public void setGno(Integer gno) {
		this.gno = gno;
	}
	
	public Integer getNums() {
		return nums;
	}
	
	public void setNums(Integer nums) {
		this.nums = nums;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
}
