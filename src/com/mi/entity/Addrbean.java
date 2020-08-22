package com.mi.entity;

public class Addrbean {
     	
	private String ano;//时间戳
	private int uid;//用户编号 外键 会员表
	private String name;//收货人姓名
	private String postcode;//邮政编号
	private String province;//省份
	private String city;//城市
	private String area;//地区
	private String addr;//详细地址
	private String tel;//收货人联系方式
	private String fixedtel;//固定电话号码
	private int flag;//是否为默认收货地址 1 默认 0 普通地址
	private int status;//状态 1 有效 0 无效 是否使用
	@Override
	public String toString() {
		return "Addrbean [ano=" + ano + ", uid=" + uid + ", name=" + name + ", postcode=" + postcode + ", province="
				+ province + ", city=" + city + ", area=" + area + ", addr=" + addr + ", tel=" + tel + ", fixedtel="
				+ fixedtel + ", flag=" + flag + ", status=" + status + "]";
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFixedtel() {
		return fixedtel;
	}
	public void setFixedtel(String fixedtel) {
		this.fixedtel = fixedtel;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
