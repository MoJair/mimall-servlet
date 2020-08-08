package com.mi.entity;

import java.io.Serializable;

public class MemberInfo implements Serializable{
	private static final long serialVersionUID = -8073922724212064694L;

	private Integer mno;
	private String nickName;
	private String realName ;
	private String pwd;
	private String tel;
	private String email;
	private String photo;
	private String regDate;
	private Integer status;
	
	@Override
	public String toString() {
		return "MemberInfo [mno=" + mno + ", nickName=" + nickName + ", realName=" + realName + ", pwd=" + pwd + ", "
				+ "tel=" + tel + ", email=" + email + ", photo=" + photo + ", regDate=" + regDate + ", status=" + status + "]";
	}
	
	public Integer getMno() {
		return mno;
	}
	
	public void setMno(Integer mno) {
		this.mno = mno;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getRealName() {
		return realName;
	}
	
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getRegDate() {
		return regDate;
	}
	
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
