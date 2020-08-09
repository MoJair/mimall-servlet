package com.mi.entity;

import java.io.Serializable;

public class MemberInfo implements Serializable{
	private static final long serialVersionUID = -8073922724212064694L;

	private Integer uid;
	private String uname ;
	//private String realName;
	private String pwd;
	private String sex;
	private String tel;
	private String email;
	//private String photo;
	//private String regDate;
	private String hobby;
	private Integer status;
	
	@Override
	public String toString() {
		return "MemberInfo [uid=" + uid + ", uname=" + uname + ", pwd=" + pwd + ", sex=" + sex + ", "
				+ "tel=" + tel + ", email=" + email + ", hobby=" + hobby + ", status=" + status + "]";
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
