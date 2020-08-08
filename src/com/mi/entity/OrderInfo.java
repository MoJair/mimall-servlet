package com.mi.entity;

import java.io.Serializable;

public class OrderInfo implements Serializable{
	private static final long serialVersionUID = -8700410980566181150L;

	private String ono;
	private String odate; //下单日期
	private String ano; //收货地址
	private String sdate; //发货日期
	private String rdate; //收货日期
	private Integer status; //订单状态
	private Double price; //订单总额
	private Integer invoice; //是否已开发票
	
	@Override
	public String toString() {
		return "OrderInfo [ono=" + ono + ", odate=" + odate + ", ano=" + ano + ", sdate=" + sdate + ", "
				+ "rdate=" + rdate + ", status=" + status + ", price=" + price + ", invoice=" + invoice + "]";
	}
	
	public String getOno() {
		return ono;
	}
	
	public void setOno(String ono) {
		this.ono = ono;
	}
	
	public String getOdate() {
		return odate;
	}
	
	public void setOdate(String odate) {
		this.odate = odate;
	}
	
	public String getAno() {
		return ano;
	}
	
	public void setAno(String ano) {
		this.ano = ano;
	}
	
	public String getSdate() {
		return sdate;
	}
	
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	
	public String getRdate() {
		return rdate;
	}
	
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Integer getInvoice() {
		return invoice;
	}
	
	public void setInvoice(Integer invoice) {
		this.invoice = invoice;
	}
}
